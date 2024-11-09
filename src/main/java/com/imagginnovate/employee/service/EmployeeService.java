package com.imagginnovate.employee.service;

import com.imagginnovate.employee.EmployeeException;
import com.imagginnovate.employee.entity.EmployeeEntity;
import com.imagginnovate.employee.mapper.EmployeeMapper;
import com.imagginnovate.employee.model.request.Employee;
import com.imagginnovate.employee.model.response.EmployeeResponse;
import com.imagginnovate.employee.model.response.EmployeeTaxResponse;
import com.imagginnovate.employee.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public String createEmployee(Employee employee) throws EmployeeException {

        if (validateEmployee(employee)) {
            EmployeeEntity employeeToSave = EmployeeMapper.mapToEmployee(employee);
            EmployeeEntity createdEmployee = employeeRepository.save(employeeToSave);
            return "Employee creation successful for" + createdEmployee.firstName +StringUtils.EMPTY+ createdEmployee.lastName;
        } else return "Employee Not Created as ";

    }

    public EmployeeTaxResponse calculateTax(Long employeeId) {
        EmployeeEntity employee = getEmployeeById(employeeId);
        EmployeeTaxResponse.EmployeeTaxResponseBuilder taxResponse = EmployeeTaxResponse.builder();
        double taxAmount = 0.0;
        double cessAmount = 0.0;
        double lop= 0.0;
        double totalYearlySalary = 0.0;
        LocalDate dateOfJoining = LocalDate.parse(employee.doj);
        LocalDate financialYearStart = LocalDate.of(LocalDate.now().getYear(), Month.APRIL, 1);
        LocalDate financialYearEnd= LocalDate.of(LocalDate.now().plusYears(1).getYear(), Month.MARCH, 31);

        long monthsWorked = ChronoUnit.MONTHS.between(dateOfJoining, financialYearEnd);
        if(dateOfJoining.isBefore(financialYearStart)){
            monthsWorked = ChronoUnit.MONTHS.between(financialYearStart,financialYearEnd);
            totalYearlySalary= employee.getSalary() * monthsWorked;
        }
        else{

            if(dateOfJoining.getDayOfMonth()!=1){
                int i = 31 - dateOfJoining.getDayOfMonth();
                lop = i * (employee.getSalary() / 30);
            }
            monthsWorked = monthsWorked -1;
            totalYearlySalary= employee.getSalary() * monthsWorked+lop;
        }
        if(totalYearlySalary > 1000000){
            taxAmount = (totalYearlySalary-1000000)*0.2+50000;
        }
        else if(totalYearlySalary> 500000){
            taxAmount = (totalYearlySalary-500000)*0.1+12500;
        }else if(totalYearlySalary>250000){
            taxAmount = (totalYearlySalary - 250000) * 0.05;
        }
        if(totalYearlySalary > 2500000){
            cessAmount = (totalYearlySalary - 2500000) * 0.02;
        }
        taxResponse.taxAmount(taxAmount)
                .cessAmount(cessAmount)
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.lastName);
    return taxResponse.build();




    }

    public List<EmployeeResponse> getEmployees() {
        List<EmployeeResponse> employees = new ArrayList<>();
        List<EmployeeEntity> employeeList = employeeRepository.findAll();
        employeeList.stream().forEach(employee -> employees.add(EmployeeMapper.mapTpEmployeeResponse(employee)));
        return employees;
    }

    public EmployeeEntity getEmployeeById(Long id) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            return null;
        }
        return employee.get();
    }

    private boolean validateEmployee(Employee employee) throws EmployeeException {

        EmployeeEntity employeeById = getEmployeeById(employee.getEmployeeId());
        validateEmail(employee.getEmail());
        validateDate(employee.getDoj());
        if (Objects.isNull(employeeById)) {
            if (!StringUtils.isNotEmpty(employee.getFirstName())
                    && !StringUtils.isNotEmpty(employee.getLastName())
                    && !StringUtils.isNotEmpty(employee.getEmail())
                    && !StringUtils.isNotEmpty(employee.getPhoneNumber())
                    && !(employee.getSalary() > 0)) {
                return false;
            }

        } else {
            return false;
        }
        return true;
    }


    private boolean validateEmail(String email) throws EmployeeException {

        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        if (StringUtils.isEmpty(email)) {
            throw new EmployeeException("Email Can't be empty");
        }
        if(pattern.matcher(email).matches()){
            return true;
        }
        else{
           throw new EmployeeException("Invalid Email Provided");
        }
    }

    private boolean validateDate(String date) throws EmployeeException {
        try {
            log.info("");
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException de) {
            log.error("Unable to parse give date", de);
            throw new EmployeeException("Unable to parse givenDate");
        }
    }


}

