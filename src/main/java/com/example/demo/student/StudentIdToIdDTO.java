package com.example.demo.student;

import org.springframework.stereotype.Service;

@Service
public class StudentIdToIdDTO {
    public StudentIdDTO studentIdToDTO(Long id){
        return new StudentIdDTO(id);
    }
}
