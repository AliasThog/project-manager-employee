package com.example.demo_session01.service.impl;

import com.example.demo_session01.mapper.DataMapper;
import com.example.demo_session01.model.dto.request.CreateEmployeeDto;
import com.example.demo_session01.model.dto.CustomResponse;
import com.example.demo_session01.model.dto.request.UpdateEmployeeDto;
import com.example.demo_session01.model.entity.Employee;
import com.example.demo_session01.exception.CustomException;
import com.example.demo_session01.repository.EmployeeRepository;
import com.example.demo_session01.service.EmployeeService;
import com.example.demo_session01.ultil.ValidationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new CustomException("Employee not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<CustomResponse> createEmployee(CreateEmployeeDto dto, BindingResult result) {
        try {
            if (result.hasErrors()) {
                throw new CustomException(ValidationUtils.getValidationErrorString(result), HttpStatus.BAD_REQUEST);
            }
            if (employeeRepository.existsByMa(dto.getMa())) {
                throw new CustomException("Ma nhan vien da ton tai", HttpStatus.CONFLICT);
            }
            Employee employee = DataMapper.toEntity(dto, Employee.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse("Employee created successfully!",
                    HttpStatus.CREATED.value(), employeeRepository.save(employee)));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(new CustomResponse(e.getMessage(), e.getHttpStatus().value(), new CreateEmployeeDto()));
        }

    }

    @Override
    public ResponseEntity<CustomResponse> updateEmployee(Long id, UpdateEmployeeDto dto, BindingResult result) {

        try {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (!employee.isPresent()) {
                throw new CustomException("id nhan vien không tồn tại", HttpStatus.BAD_REQUEST);
            }
            if (result.hasErrors()) {
                throw new CustomException(ValidationUtils.getValidationErrorString(result), HttpStatus.BAD_REQUEST);
            }
            if (!employeeRepository.existsByMa(dto.getMa())) {
                throw new CustomException("Không được thay đổi mã NV", HttpStatus.BAD_REQUEST);
            }
            Employee updatedEmployee = DataMapper.toEntity(dto, Employee.class);
            updatedEmployee.setId(id);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse("Employee update successfully!",
                    HttpStatus.OK.value(), employeeRepository.save(updatedEmployee)));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(new CustomResponse(e.getMessage(), e.getHttpStatus().value(), new CreateEmployeeDto()));
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id).ifPresentOrElse(employee -> {
            employeeRepository.delete(employee);
        }, () -> {
            throw new CustomException("Không tìm thấy nhân viên", HttpStatus.NOT_FOUND);
        });

    }
}

