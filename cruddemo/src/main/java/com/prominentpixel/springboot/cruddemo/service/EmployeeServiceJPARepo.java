package com.prominentpixel.springboot.cruddemo.service;

import com.prominentpixel.springboot.cruddemo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceJPARepo {
    List<Employee> findAll();

    Employee find(int id);

    void save(Employee employee);

    void deleteById(int id);
}
