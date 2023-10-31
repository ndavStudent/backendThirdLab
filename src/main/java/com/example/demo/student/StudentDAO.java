package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class StudentDAO {
    private final StudentToDTO studentToDTO;
    private final StudentRepository studentRepository;

    public StudentDAO(StudentToDTO studentToDTO, StudentRepository studentRepository1) {
        this.studentToDTO = studentToDTO;
        this.studentRepository = studentRepository1;
    }

    public Stream<Student> getAllStudents() {
        return Streamable.of(studentRepository.findAll()).stream();
    }

    public StudentDTO addNewStudent(StudentDTO studentDTO) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(studentDTO.email());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(new Student(
                studentDTO.id(),
                studentDTO.name(),
                studentDTO.email(),
                studentDTO.dob()
        ));
        return studentDTO;
    }

    @Transactional
    public StudentDTO updateStudent(Long studentId, String name, String email) {
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
        return studentToDTO.studentToDTO(student);
    }

    public void deleteStudentById(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }
}
