package com.imagginnovate.employee.mapper;

import com.imagginnovate.employee.entity.EmployeeEntity;
import com.imagginnovate.employee.model.request.Employee;
import com.imagginnovate.employee.model.response.EmployeeResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeeMapper {


    public EmployeeEntity mapToEmployee(Employee employee){
        return EmployeeEntity.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .doj(employee.getDoj())
                .salary(employee.getSalary())
                .phoneNumber(employee.getPhoneNumber())
                .build();
    }

    public EmployeeResponse mapTpEmployeeResponse(EmployeeEntity employee){
        return EmployeeResponse.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .build();
    }
}
