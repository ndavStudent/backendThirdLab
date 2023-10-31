package com.example.demo.student;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface StudentToDTO {
    public StudentDTO toDTO(Student student){
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDob(),
                student.getAge());
    }
}
