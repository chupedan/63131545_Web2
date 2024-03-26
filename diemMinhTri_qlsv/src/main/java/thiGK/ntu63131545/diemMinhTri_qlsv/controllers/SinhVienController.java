package thiGK.ntu63131545.diemMinhTri_qlsv.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import thiGK.ntu63131545.diemMinhTri_qlsv.models.SinhVien;
import thiGK.ntu63131545.diemMinhTri_qlsv.services.SinhVienService;

@Controller
@RequestMapping("/sinhVien")
public class SinhVienController {
	 @Autowired
	 private SinhVienService svService;
	 
	@GetMapping("/danhsachSV")
	public String listStudent(Model model,  
								@RequestParam("page") Optional<Integer> page,
								@RequestParam("size") Optional<Integer> size) {
		final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);
        Page<SinhVien> svPage = svService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
		model.addAttribute("dsSV", svPage);
		
		int totalPages = svPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "sinhvien_getAll";
	}
	
	@GetMapping("/timSinhVien")
	public String timSinhVien(@RequestParam("maSoSV") String maSoSV, Model model) {
	    List<SinhVien> sinhVienList = svService.timSinhVienTheoMa(maSoSV);
	    model.addAttribute("sinhVienList", sinhVienList);
	    return "timSinhVien";
	}
	
	@GetMapping("/themSinhVien")
    public String hienThiFormThemSinhVien(Model model) {
        model.addAttribute("sinhVien", new SinhVien());
        return "formThemSinhVien";
    }

    @PostMapping("/themSinhVien")
    public String xuLyThemSinhVien(SinhVien sinhVien) {
    	svService.themSinhVien(sinhVien);
        return "redirect:/";
    }

	
}