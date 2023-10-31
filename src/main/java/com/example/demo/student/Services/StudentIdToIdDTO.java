package com.example.demo.student.Services;

import com.example.demo.student.DTO.StudentIdDTO;
import org.springframework.stereotype.Service;

@Service
public class StudentIdToIdDTO {
    public StudentIdDTO studentIdToDTO(Long id){
        return new StudentIdDTO(id);
    }
}
