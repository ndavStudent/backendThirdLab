package com.example.demo.student.Services;

import com.example.demo.student.DAO.StudentDAO;
import com.example.demo.student.DTO.StudentDTO;
import com.example.demo.student.DTO.StudentDescDTO;
import com.example.demo.student.DTO.StudentIdDTO;
import com.example.demo.student.DTO.StudentRenewDTO;
import com.example.demo.student.Services.StudentDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentDAO studentDAO;
    private final StudentDTOMapper studentDTOMapper;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
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


    public StudentDTO updateStudent(StudentDescDTO studentDescDTO) {
        return studentDAO.updateStudent(studentDescDTO);
    }

    public StudentDTO putUpdateStudent(Long studentId, StudentRenewDTO studentRenewDTO){
        return studentDAO.putUpdateStudent(studentId, studentRenewDTO);
    }
}
