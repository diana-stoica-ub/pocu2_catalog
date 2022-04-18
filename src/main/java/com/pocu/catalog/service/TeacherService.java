package com.pocu.catalog.service;

import com.pocu.catalog.entity.TeacherEntity;
import com.pocu.catalog.repository.TeacherRepository;
import com.pocu.catalog.web.dto.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service                //this class is a Service bean
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<TeacherEntity> getAllTeachers() {
        return teacherRepository.findAll();
    }

    //firstName and lastName can be both set up or not
    public List<TeacherEntity> getAllTeachersByName(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return teacherRepository.findByFirstNameAndLastName(firstName, lastName);
        } else if (firstName != null) {
            return teacherRepository.findByFirstName(firstName);
        } else {
            return teacherRepository.findByLastName(lastName);
        }
    }

    public TeacherEntity saveTeacher(TeacherEntity teacherEntity) {
        return teacherRepository.save(teacherEntity);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public void deleteTeacherByCnp(String cnp) {
        teacherRepository.deleteByCnp(cnp);
    }

    public void deleteAllTeachers() {
        teacherRepository.deleteAll();
    }
}
