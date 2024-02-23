package com.example.demo_session01.service.impl;

import com.example.demo_session01.entity.Employee;
import com.example.demo_session01.exception.CustomException;
import com.example.demo_session01.repository.EmployeeRepository;
import com.example.demo_session01.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.findByMa(employee.getMa()) != null) {
            throw new CustomException("Ma nhan vien da ton tai", HttpStatus.CONFLICT);
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (!employee.isPresent()) {
            throw new CustomException("ko tim thay nhan vien voi id " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!employee.get().getMa().equals(employeeDetails.getMa())) {
            throw new CustomException("Không được thay đổi mã nhân viên", HttpStatus.BAD_REQUEST);
        }
        Employee updatedEmployee = employee.get();
        updatedEmployee.setMa(employeeDetails.getMa());
        updatedEmployee.setTen(employeeDetails.getTen());
        updatedEmployee.setDiaChi(employeeDetails.getDiaChi());
        updatedEmployee.setEmail(employeeDetails.getEmail());
        updatedEmployee.setId(employeeDetails.getId());
        updatedEmployee.setViTriCongViec(employeeDetails.getViTriCongViec());
        return employeeRepository.save(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id).ifPresentOrElse(employee ->{
            employeeRepository.delete(employee);
        },()->{
            throw new CustomException("Không tìm thấy nhân viên",HttpStatus.NOT_FOUND);
        });

    }
}

