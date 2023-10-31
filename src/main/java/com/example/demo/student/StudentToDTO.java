package com.example.demo.student;

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
