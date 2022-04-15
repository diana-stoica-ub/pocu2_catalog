package com.pocu.catalog.service;

import com.pocu.catalog.web.dto.SubjectDto;
import com.pocu.catalog.web.dto.TeacherDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service                //this class is a Service bean
public class TeacherService {

    public TeacherDto getTeacher() {                //dummy method to create a teacher object; will be removed after database integration
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(1L);
        teacherDto.setFirstName("John");
        teacherDto.setLastName("Doe");
        teacherDto.setDateOfBirth(LocalDate.of(1990,2, 20));
        teacherDto.setSubjectDto(getSubject());

        return teacherDto;
    }

    private SubjectDto getSubject() {        //dummy method to create a teacher object; will be removed after database integration
        SubjectDto dto = new SubjectDto();
        dto.setId(1L);
        dto.setCreditPoints(5);
        dto.setName("Math");
        dto.setOptional(true);

        return dto;
    }
}
