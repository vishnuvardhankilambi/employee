package com.imagginnovate.employee.controller;


import com.imagginnovate.employee.model.request.Employee;
import com.imagginnovate.employee.model.response.EmployeeTaxResponse;
import com.imagginnovate.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.PreparedStatement;

@Slf4j
@RestController
public class TaxCalculatorController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("/calculateTax/{employeeId}")
    public ResponseEntity<EmployeeTaxResponse> getEmployeeTax(@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.calculateTax(employeeId));
    }
}
