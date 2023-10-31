package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentDAO studentDAO;
    private final StudentDTOMapper studentDTOMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
        studentDTOMapper = new StudentDTOMapper();
    }


    public List<StudentDTO> getStudents() {
        return studentDAO.getAllStudents().map(studentDTOMapper)
                .collect(Collectors.toList());
    }


    public StudentDTO addNewStudent(StudentDTO studentDTO) {
        return studentDAO.addNewStudent(studentDTO);
    }

    public void deleteStudent(StudentIdDTO studentId) {
        studentDAO.deleteStudentById(studentId.id());
    }


    public StudentDTO updateStudent(Long studentId, String name, String email) {
        return studentDAO.updateStudent(studentId, name, email);
    }
}
