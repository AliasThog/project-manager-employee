package com.example.demo_session01.service;

import com.example.demo_session01.model.dto.request.CreateEmployeeDto;
import com.example.demo_session01.model.dto.CustomResponse;
import com.example.demo_session01.model.dto.request.UpdateEmployeeDto;
import com.example.demo_session01.model.entity.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    ResponseEntity<CustomResponse> createEmployee(CreateEmployeeDto dto, BindingResult result);

    ResponseEntity<CustomResponse> updateEmployee(Long id, UpdateEmployeeDto dto, BindingResult result);

    void deleteEmployee(Long id);

}
