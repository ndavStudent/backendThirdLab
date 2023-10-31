package com.example.demo.student.Controllers;

import com.example.demo.student.DTO.StudentDTO;
import com.example.demo.student.DTO.StudentRenewDTO;
import com.example.demo.student.Services.*;
import com.example.demo.student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;
    private final StudentToDTO studentToDTO;
    private final StudentIdToIdDTO studentIdToIdDTO;
    private final StudentDescToDTO studentDescToDTO;

    @Autowired
    public StudentController(StudentService studentService, StudentToDTO studentToDTO, StudentIdToIdDTO studentIdToIdDTO, StudentDescToDTO studentDescToDTO) {
        this.studentService = studentService;
        this.studentToDTO = studentToDTO;
        this.studentIdToIdDTO = studentIdToIdDTO;
        this.studentDescToDTO = studentDescToDTO;
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

    @PatchMapping(path = "{studentId}")
    public StudentDTO patchUpdateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        return studentService.updateStudent(studentDescToDTO.studentDescToDTO(studentId, name, email));
    }
    @PutMapping(path = "{studentId}")
    public StudentDTO putUpdateStudent(
            @RequestBody StudentRenewDTO studentNewDTO,
            @PathVariable("studentId") Long studentId){
        return studentService.putUpdateStudent(studentId, studentNewDTO);
    }

}
