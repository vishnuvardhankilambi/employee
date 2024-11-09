package com.imagginnovate.employee.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Employee {

    @NotNull
    public Long employeeId;
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @NotBlank
    @Email
    public String email;
    @NotBlank
    public String phoneNumber;
    @NotBlank
    public String doj;

    public Double salary;

}
