package com.pocu.catalog.service;

import com.pocu.catalog.entity.StudentEntity;
import com.pocu.catalog.exception.StudentNotFoundException;
import com.pocu.catalog.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private static final String STUDENT_NOT_FOUND_CODE = "STUDENT_NOT_FOUND";
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentEntity> getStudents(Integer page, Integer size) {
        return studentRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public StudentEntity saveStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    public StudentEntity getStudent(Long id) {
        Optional<StudentEntity> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            return studentOptional.get();
        } else {
            throw new StudentNotFoundException("Student not found", STUDENT_NOT_FOUND_CODE);
        }
    }
}
