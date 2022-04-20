package com.pocu.catalog.service;

import com.pocu.catalog.entity.TeacherEntity;
import com.pocu.catalog.exception.TeacherNotFoundException;
import com.pocu.catalog.repository.TeacherRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceTest {

    @InjectMocks
    private TeacherService teacherService;

    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private SubjectService subjectService;

    @Test
    public void testGetTeacherFound() {
        Mockito.when(teacherRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(getTeacher()));

        TeacherEntity teacher = teacherService.getTeacher(1L);
        Assert.assertEquals("FirstName", teacher.getFirstName());
        Mockito.verify(teacherRepository, Mockito.times(1)).findById(1L);
    }

    @Test(expected = TeacherNotFoundException.class)
    public void testGetTeacherNotFound() {
        Mockito.when(teacherRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        TeacherEntity teacher = teacherService.getTeacher(1L);
    }

    private TeacherEntity getTeacher() {
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setFirstName("FirstName");

        return teacherEntity;
    }
}
