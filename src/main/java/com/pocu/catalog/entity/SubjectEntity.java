package com.pocu.catalog.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subject")
public class SubjectEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "credit_points")
    private Integer creditPoints;

    @Column(name = "is_optional")
    private Boolean optional;

    @ManyToMany(mappedBy = "enrolledSubjects")
    List<StudentEntity> enrolledStudents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(Integer creditPoints) {
        this.creditPoints = creditPoints;
    }

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public List<StudentEntity> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<StudentEntity> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }
}
