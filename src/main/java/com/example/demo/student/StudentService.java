package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository StudentRepository;

    @Autowired
    public StudentService(com.example.demo.student.StudentRepository studentRepository) {
        StudentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return StudentRepository.findAll();
//        return List.of(
//                new Student(
//                        1L,
//                        "Bob",
//                        "Bob.Alice@gmail.com",
//                        LocalDate.of(2000, Month.JANUARY, 1),
//                        23
//                )
//        );
    }
}
