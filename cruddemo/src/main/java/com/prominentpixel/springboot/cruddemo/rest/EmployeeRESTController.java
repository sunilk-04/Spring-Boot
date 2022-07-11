package com.prominentpixel.springboot.cruddemo.rest;

// Uncomment below code if you don't want to use Spring data REST

/*
import com.prominentpixel.springboot.cruddemo.dao.EmployeeRepository;
import com.prominentpixel.springboot.cruddemo.entity.Employee;
import com.prominentpixel.springboot.cruddemo.service.EmployeeService;
import com.prominentpixel.springboot.cruddemo.service.EmployeeServiceJPARepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRESTController {

    */
/* private EmployeeService employeeService; *//*

    private EmployeeServiceJPARepo employeeServiceJPARepo;

    public EmployeeRESTController(*/
/* EmployeeService employeeService ,*//*
 EmployeeServiceJPARepo employeeServiceJPARepo) {
        // this.employeeService = employeeService;
        this.employeeServiceJPARepo = employeeServiceJPARepo;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        // return this.employeeService.findAll();
        return this.employeeServiceJPARepo.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee get(@PathVariable int employeeId) {
        // Employee employee = this.employeeService.find(employeeId);
        Employee employee = this.employeeServiceJPARepo.find(employeeId);

        if (employee == null) {
            throw new RuntimeException("Couldn't find employee with id : " + employeeId);
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee add(@RequestBody Employee employee) {
        employee.setId(0);
        // this.employeeService.save(employee);
        this.employeeServiceJPARepo.save(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee update(@RequestBody Employee employee) {
        // this.employeeService.save(employee);
        this.employeeServiceJPARepo.save(employee);
        return employee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String delete(@PathVariable int employeeId) {
        // Employee employee = this.employeeService.find(employeeId);
        Employee employee = this.employeeServiceJPARepo.find(employeeId);

        if (employee == null) {
            throw new RuntimeException("Couldn't find employee with id : " + employeeId);
        }

        this.employeeServiceJPARepo.deleteById(employeeId);
        return "Deleted employee id : " + employeeId;
    }
}
*/
