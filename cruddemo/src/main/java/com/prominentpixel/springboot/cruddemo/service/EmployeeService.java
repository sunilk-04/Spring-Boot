package com.prominentpixel.springboot.cruddemo.service;

import com.prominentpixel.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    public Employee find(int id);

    public void save(Employee employee);

    public void delete(int id);
}
