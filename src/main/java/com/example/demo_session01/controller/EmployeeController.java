package com.example.demo_session01.controller;


import com.example.demo_session01.model.dto.request.CreateEmployeeDto;
import com.example.demo_session01.model.dto.CustomResponse;
import com.example.demo_session01.model.dto.request.UpdateEmployeeDto;
import com.example.demo_session01.model.entity.Employee;
import com.example.demo_session01.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }

    @GetMapping("/{id123}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id123") Long id) {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
    }

    @PostMapping()
    public ResponseEntity<CustomResponse> createEmployee(@Valid @RequestBody CreateEmployeeDto dto, BindingResult result) {
        return employeeService.createEmployee(dto, result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> updateEmployee(@PathVariable Long id, @RequestBody UpdateEmployeeDto dto, BindingResult result) {
        return employeeService.updateEmployee(id, dto, result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}