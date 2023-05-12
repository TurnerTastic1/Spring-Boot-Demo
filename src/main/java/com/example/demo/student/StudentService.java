package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository StudentRepository;

    @Autowired
    public StudentService(com.example.demo.student.StudentRepository studentRepository) {
        StudentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return StudentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = StudentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        StudentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = StudentRepository.existsById(studentId);
        if (!exists) {
        	throw new IllegalStateException("student with id " + studentId + " does not exists");
        }
        StudentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        	Student student = StudentRepository.findById(studentId)
        			.orElseThrow(() -> new IllegalStateException(
        					"student with id " + studentId + " does not exists"));
        	if (name != null && name.length() > 0 && !student.getName().equals(name)) {
        		student.setName(name);
        	}
        	if (email != null && email.length() > 0 && !student.getEmail().equals(email)) {
        		Optional<Student> studentOptional = StudentRepository.findStudentByEmail(email);
        		if (studentOptional.isPresent()) {
        			throw new IllegalStateException("email taken");
        		}
        		student.setEmail(email);
        	}
        	StudentRepository.save(student);
    }
}
