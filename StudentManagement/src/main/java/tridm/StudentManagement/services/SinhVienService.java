package tridm.StudentManagement.services;

import java.util.List;

import tridm.StudentManagement.models.SinhVien;

public interface SinhVienService { 
    List<SinhVien> getAllSinhVien();

    SinhVien saveSinhVien(SinhVien sv);

    SinhVien getSinhVienById(Long id);

    SinhVien updateSinhVien(SinhVien sv);

    void deleteSinhVien(Long id);
}
