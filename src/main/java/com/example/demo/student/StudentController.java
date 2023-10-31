package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;
    private final StudentToDTO studentToDTO;
    private final StudentIdToIdDTO studentIdToIdDTO;

    @Autowired
    public StudentController(StudentService studentService, StudentToDTO studentToDTO, StudentIdToIdDTO studentIdToIdDTO) {
        this.studentService = studentService;
        this.studentToDTO = studentToDTO;
        this.studentIdToIdDTO = studentIdToIdDTO;
    }

    @GetMapping
    public List<StudentDTO> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public StudentDTO registerNewStudent(@RequestBody Student student) {
        return studentService.addNewStudent(studentToDTO.studentToDTO(student));
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentIdToIdDTO.studentIdToDTO(studentId));

    }

    @PutMapping(path = "{studentId}")
    public StudentDTO updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        return studentService.updateStudent(studentId, name, email);
    }

}
