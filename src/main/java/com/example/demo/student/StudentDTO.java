package com.example.demo.student;

import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

public record StudentDTO(
        Long id,

        String name,
        String email,
        LocalDate dob,
        Integer age
) {
}
