package com.pocu.catalog.web;

import com.pocu.catalog.service.TeacherService;
import com.pocu.catalog.web.dto.TeacherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController     //this means that this class will become a Bean (class managed by Spring) and will contain endpoints
                    //an endpoint is a method that handles HTTP requests and returns HTTP response
@RequestMapping(value = "/teacher")         //this will be the base of all the endpoints from this controller
public class TeacherController {

    private final TeacherService teacherService;

    @Value("${test.value}")         //retrieves a value from application.yml file
    private String testValue;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(value = "/test")
    public String test() {
        return testValue;
    }

    @GetMapping(value = "")
    public TeacherDto getTeacher() {
        return teacherService.getTeacher();
    }

}
