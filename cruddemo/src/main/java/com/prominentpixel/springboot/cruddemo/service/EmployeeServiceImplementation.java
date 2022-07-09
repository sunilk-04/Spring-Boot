package com.prominentpixel.springboot.cruddemo.service;

import com.prominentpixel.springboot.cruddemo.dao.EmployeeDAO;
import com.prominentpixel.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImplementation(@Qualifier("employeeDAOJPAImplementation") EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> findAll() {
        return this.employeeDAO.findAll();
    }

    @Override
    @Transactional
    public Employee find(int id) {
        return this.employeeDAO.find(id);
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        this.employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public void delete(int id) {
        this.employeeDAO.delete(id);
    }
}
