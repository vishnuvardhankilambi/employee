package com.imagginnovate.employee.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class EmployeeResponse {
    public Long employeeId;
    public String firstName;

    public String lastName;

    public String email;

    public String phoneNumber;

}
