package com.example.demo.student.DAO;

import com.example.demo.student.DTO.StudentDTO;
import com.example.demo.student.DTO.StudentDescDTO;
import com.example.demo.student.DTO.StudentRenewDTO;
import com.example.demo.student.Services.StudentPutUpdateToDTO;
import com.example.demo.student.entity.Student;
import com.example.demo.student.repositories.StudentRepository;
import com.example.demo.student.Services.StudentToDTO;
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
    public StudentDTO updateStudent(StudentDescDTO studentDescDTO) {
        Long studentId = studentDescDTO.id();
        String name = studentDescDTO.name();
        String email = studentDescDTO.email();
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

    @Transactional
    public StudentDTO putUpdateStudent(Long studentId, StudentRenewDTO studentRenewDTO){
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new IllegalStateException("student with id " + studentId + "does not exists"));
        if (studentRenewDTO.name() == null){
            throw new IllegalStateException("name must be not null value");
        }
        if (studentRenewDTO.email() == null){
            throw new IllegalStateException("email must be not null value");
        }
        if (studentRenewDTO.dob() == null){
            throw new IllegalStateException("dob must be not null value");
        }
        student.setName(studentRenewDTO.name());
        student.setEmail(studentRenewDTO.email());
        student.setDob(studentRenewDTO.dob());
        return studentToDTO.studentToDTO(new Student(studentId, studentRenewDTO.name(), studentRenewDTO.email(), studentRenewDTO.dob()));
    }
}
