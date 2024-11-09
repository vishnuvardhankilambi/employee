package com.imagginnovate.employee.model.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Employee {

    @NotNull(message = "employeeId is mandatory")
    public Long employeeId;
    @NotBlank(message = "firstName is mandatory")
    public String firstName;
    @NotBlank(message = "lastName is mandatory")
    public String lastName;
    @NotBlank
    @Email(message = "email should be valid")
    public String email;
    @NotBlank(message = "phoneNumber is mandatory")
    public String phoneNumber;
    @NotBlank(message = "Date of Joining is mandatory")
    public String doj;
    @DecimalMin(value = "1.0", message = "salary is mandatory")
    public Double salary;

}
