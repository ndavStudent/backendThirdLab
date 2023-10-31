package com.example.demo.student.Services;

import com.example.demo.student.DTO.StudentDTO;
import com.example.demo.student.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentToDTO {
    public StudentDTO studentToDTO(Student student){
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDob(),
                student.getAge());
    }
}
