package com.pocu.catalog.web.dto;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

public class StudentDto extends BaseDto {

    @NotEmpty(message = "firstName.cannot.be.null")
    private String firstName;
    @NotEmpty(message = "lastName.cannot.be.null")
    private String lastName;
    private BigDecimal averageGrade;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(BigDecimal averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", averageGrade=" + averageGrade +
                "} " + super.toString();
    }
}
