package com.example.demo.student.DTO;

import java.time.LocalDate;

public record StudentRenewDTO(
        String name,
        String email,
        LocalDate dob
) {
}
