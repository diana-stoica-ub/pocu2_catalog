package com.pocu.catalog.config;

import com.pocu.catalog.entity.SubjectEntity;
import com.pocu.catalog.entity.TeacherEntity;
import com.pocu.catalog.service.SubjectService;
import com.pocu.catalog.service.TeacherService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnExpression("${insert.test.data}")
public class DataSetup implements ApplicationRunner {

    private final SubjectService subjectService;
    private final TeacherService teacherService;

    public DataSetup(SubjectService subjectService, TeacherService teacherService) {
        this.subjectService = subjectService;
        this.teacherService = teacherService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        saveTeacher("FirstName1", "LastName1", "123456790", 10L);
        saveTeacher("FirstName2", "LastName2", "123456791", 15L);
        saveTeacher("FirstName3", "LastName3", "123456792", 22L);
        saveTeacher("FirstName4", "LastName4", "123456793", 22L);
        saveTeacher("FirstName5", "LastName5", "123456794", 3L);
        saveTeacher("FirstName6", "LastName6", "123456790", 12L);
        saveTeacher("FirstName7", "LastName7", "123456791", 5L);
        saveTeacher("FirstName8", "LastName8", "123456792", 22L);
        saveTeacher("FirstName9", "LastName9", "123456793", 4L);
        saveTeacher("FirstName10", "LastName10", "123456794", 2L);

        saveSubject("Subject1", true, 5);
        saveSubject("Subject2", true, 2);
        saveSubject("Subject3", false, 4);
        saveSubject("Subject4", false, 5);
        saveSubject("Subject5", true, 5);
    }

    private void saveSubject(String name, Boolean optional, Integer creditPoints) {
        SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.setName(name);
        subjectEntity.setOptional(optional);
        subjectEntity.setCreditPoints(creditPoints);

        subjectService.saveSubject(subjectEntity);
    }

    private void saveTeacher(String firstName, String lastName, String cnp, Long salary) {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setFirstName(firstName);
        teacherEntity.setLastName(lastName);
        teacherEntity.setCnp(cnp);
        teacherEntity.setSalary(salary);

        teacherService.saveTeacher(teacherEntity);
    }
}
