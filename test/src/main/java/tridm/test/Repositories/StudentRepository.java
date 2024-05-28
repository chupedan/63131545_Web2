package tridm.test.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tridm.test.Models.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
    @Query("SELECT s FROM Student s WHERE s.name LIKE %?1%")
    List<Student> searchStudent(String keyword);
}