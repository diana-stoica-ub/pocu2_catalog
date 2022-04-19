package com.pocu.catalog.web;

import com.pocu.catalog.converter.StudentConverter;
import com.pocu.catalog.entity.StudentEntity;
import com.pocu.catalog.service.StudentService;
import com.pocu.catalog.web.dto.StudentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;
    private final StudentConverter studentConverter;

    @Autowired
    public StudentController(StudentService studentService, StudentConverter studentConverter) {
        this.studentService = studentService;
        this.studentConverter = studentConverter;
    }

    @GetMapping(value = "")
    public List<StudentDto> getStudents(@RequestParam(name = "page") Integer page,
                                        @RequestParam(name = "size") Integer size) {
        logger.debug("Get all students - page {} size {}", page, size);
        return studentConverter.fromEntitiesToDtos(studentService.getStudents(page, size));
    }

    @GetMapping(value = "/{id}")
    public StudentDto getStudent(@PathVariable(name = "id") Long id) {
        logger.debug("Get student by id {}", id);

        return studentConverter.fromEntityToDto(studentService.getStudent(id));
    }

    @PostMapping(value = "")
    public StudentDto saveStudent(@Valid @RequestBody StudentDto studentDto) {
        logger.debug("Save student {}", studentDto.toString());

        StudentEntity studentEntity = studentService.saveStudent(studentConverter.fromDtoToEntity(studentDto));
        logger.debug("Student saved with id {}", studentEntity.getId());

        return studentConverter.fromEntityToDto(studentEntity);
    }
}
