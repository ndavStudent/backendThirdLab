package com.example.demo.student.Services;

import com.example.demo.student.DTO.StudentDescDTO;
import org.springframework.stereotype.Service;

@Service
public class StudentDescToDTO {
    public StudentDescDTO studentDescToDTO(Long id, String name, String email){
        return new StudentDescDTO(
                id,
                name,
                email
        );
    }
}
