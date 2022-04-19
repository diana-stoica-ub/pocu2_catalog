package com.pocu.catalog.web.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SubjectDto extends BaseDto{

    private Long id;

    @NotEmpty(message = "name.cannot.be.empty")
    private String name;
    @NotNull(message = "optional.cannot.be.empty")
    private Boolean optional;
    @NotNull(message = "creditPoints.cannot.be.empty")
    private Integer creditPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public Integer getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(Integer creditPoints) {
        this.creditPoints = creditPoints;
    }
}
