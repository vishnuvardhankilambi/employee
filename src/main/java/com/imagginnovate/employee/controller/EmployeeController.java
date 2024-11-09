package com.imagginnovate.employee.controller;

import com.imagginnovate.employee.EmployeeException;
import com.imagginnovate.employee.model.request.Employee;
import com.imagginnovate.employee.model.response.EmployeeResponse;
import com.imagginnovate.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody @Valid Employee employee) throws EmployeeException {
    return ResponseEntity.ok(employeeService.createEmployee(employee));


    }
    @GetMapping("/getEmployees")
    public ResponseEntity<List<EmployeeResponse>> getEmployees(){
        return ResponseEntity.ok(employeeService.getEmployees());
    }
}
