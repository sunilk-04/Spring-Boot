package com.prominentpixel.springdatasolr.controller;

import com.prominentpixel.springdatasolr.model.Employee;
import com.prominentpixel.springdatasolr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostConstruct
    public void add() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(112, "Sagar", new String[]{"Bangalore", "Karnataka"}));
        employees.add(new Employee(113, "Santosh", new String[]{"Hyderabad", "Andhra Pradesh"}));
        employees.add(new Employee(114, "Jay", new String[]{"Pune", "Maharashtra"}));
        this.employeeRepository.saveAll(employees);
    }

    @GetMapping("/employees")
    public Iterable<Employee> get() {
        return this.employeeRepository.findAll();
    }

    @GetMapping("employees/{id}")
    public Employee getById(@PathVariable int id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

}
