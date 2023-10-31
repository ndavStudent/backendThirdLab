package com.example.demo.student.DTO;

import java.time.LocalDate;

public record StudentDTO(
        Long id,

        String name,
        String email,
        LocalDate dob,
        Integer age
) {
}
