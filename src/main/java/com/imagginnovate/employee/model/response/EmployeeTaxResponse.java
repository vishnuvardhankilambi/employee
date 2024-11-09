package com.imagginnovate.employee.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class EmployeeTaxResponse {

    public Long employeeId;

    public String firstName;

    public String lastName;

    public Double yearlySalary;

    public Double taxAmount;

    public Double cessAmount;
}
