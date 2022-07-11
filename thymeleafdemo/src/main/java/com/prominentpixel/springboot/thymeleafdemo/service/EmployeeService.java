package com.prominentpixel.springboot.thymeleafdemo.service;

import com.prominentpixel.springboot.thymeleafdemo.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    Employee findById(int id);
    void save(Employee employee);
    void deleteById(int id);
}
