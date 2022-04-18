package com.pocu.catalog.repository;

import com.pocu.catalog.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

    List<TeacherEntity> findByFirstName(String firstName);

    List<TeacherEntity> findByLastName(String lastName);

    List<TeacherEntity> findByFirstNameAndLastName(String firstName, String lastName);

    void deleteByCnp(String cnp);
}
