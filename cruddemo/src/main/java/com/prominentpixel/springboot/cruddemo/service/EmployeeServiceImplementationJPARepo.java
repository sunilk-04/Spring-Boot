package com.prominentpixel.springboot.cruddemo.service;

import com.prominentpixel.springboot.cruddemo.dao.EmployeeRepository;
import com.prominentpixel.springboot.cruddemo.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementationJPARepo implements EmployeeServiceJPARepo {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImplementationJPARepo(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee find(int id) {
        Optional<Employee> result = this.employeeRepository.findById(id);
        Employee employee = null;
        if (result.isPresent()) {
            employee = result.get();
        }
        else {
            throw new RuntimeException("Couldn't find employee with id : " + id);
        }
        return employee;
    }

    @Override
    public void save(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        this.employeeRepository.deleteById(id);
    }
}
