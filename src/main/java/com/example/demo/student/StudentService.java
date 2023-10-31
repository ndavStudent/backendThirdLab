package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentDAO studentDAO;
    private final StudentDTOMapper studentDTOMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentDAO studentDAO) {
        this.studentRepository = studentRepository;
        this.studentDAO = studentDAO;
        studentDTOMapper = new StudentDTOMapper();
    }


    public List<StudentDTO> getStudents() {
        return studentDAO.getAllStudents().map(studentDTOMapper)
                .collect(Collectors.toList());
    }


    public StudentDTO addNewStudent(Student student) {
        return studentDAO.addNewStudent(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new IllegalStateException("student with id " + studentId + "does not exists"));
        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
            System.out.println("got it!");
            student.setName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
