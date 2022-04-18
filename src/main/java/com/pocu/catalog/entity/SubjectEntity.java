package com.pocu.catalog.entity;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class SubjectEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "credit_points")
    private Integer creditPoints;

    @Column(name = "is_optional")
    private Boolean optional;

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
}
