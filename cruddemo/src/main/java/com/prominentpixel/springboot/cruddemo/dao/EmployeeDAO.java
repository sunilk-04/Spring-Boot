package com.prominentpixel.springboot.cruddemo.dao;

import com.prominentpixel.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    public Employee find(int id);

    public void save(Employee employee);

    public void delete(int id);
}
