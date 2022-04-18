package com.pocu.catalog.web;

import com.pocu.catalog.converter.TeacherConverter;
import com.pocu.catalog.entity.TeacherEntity;
import com.pocu.catalog.service.TeacherService;
import com.pocu.catalog.web.dto.TeacherDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController     //this means that this class will become a Bean (class managed by Spring) and will contain endpoints
                    //an endpoint is a method that handles HTTP requests and returns HTTP response
@RequestMapping(value = "/teacher")         //this will be the base of all the endpoints from this controller
public class TeacherController {

    private Logger logger = LoggerFactory.getLogger(TeacherController.class);

    private final TeacherService teacherService;
    private final TeacherConverter teacherConverter;

    @Value("${test.value}")         //retrieves a value from application.yml file
    private String testValue;

    @Autowired
    public TeacherController(TeacherService teacherService, TeacherConverter teacherConverter) {
        this.teacherService = teacherService;
        this.teacherConverter = teacherConverter;
    }

    @GetMapping(value = "/test")
    public String test() {
        return testValue;
    }

    @GetMapping(value = "")
    public List<TeacherDto> getAll(@RequestParam(name = "firstName", required = false) String firstName,
                                   @RequestParam(name = "lastName", required = false) String lastName){
        List<TeacherEntity> teacherEntities;

        if (firstName != null || lastName != null) {
            teacherEntities = teacherService.getAllTeachersByName(firstName, lastName);
        } else {
            teacherEntities = teacherService.getAllTeachers();
        }

        return teacherConverter.fromEntitiesToDtos(teacherEntities);
    }

    @PostMapping(value = "")
    public TeacherDto saveTeacher(@RequestBody TeacherDto teacherDto) {
        logger.debug("Save new teacher {}", teacherDto);
        TeacherEntity teacherEntity = teacherConverter.fromDtoToEntity(teacherDto);

        teacherEntity = teacherService.saveTeacher(teacherEntity);

        logger.debug("Teacher saved - id {}", teacherEntity.getId());
        return teacherConverter.fromEntityToDto(teacherEntity);
    }

    @DeleteMapping(value = "")
    public void deleteTeacher(@RequestParam(name = "id", required = false) Long id,
                              @RequestParam(name = "cnp", required = false) String cnp) {

        if (id != null) {
            logger.debug("Delete teacher with id {}", id);
            teacherService.deleteTeacher(id);
        } else if (cnp != null) {
            logger.debug("Delete teacher with cnp {}", cnp);
            teacherService.deleteTeacherByCnp(cnp);
        } else {
            logger.debug("Delete all teachers");
            teacherService.deleteAllTeachers();
        }
    }

}
