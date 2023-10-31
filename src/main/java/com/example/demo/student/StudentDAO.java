package com.example.demo.student;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

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
    public Stream<Student> getAllStudents(){

//        list = StreamSupport.stream(studentRepository.findAll().spliterator(), false);
        return Streamable.of(studentRepository.findAll()).stream();
    }
    public StudentDTO addNewStudent(Student student){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
        return studentToDTO.toDTO(student);
    }
}
