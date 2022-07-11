package com.prominentpixel.springboot.thymeleafdemo.controller;

import com.prominentpixel.springboot.thymeleafdemo.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private List<Employee> employees;

    @PostConstruct
    public void load() {
        Employee employee1 = new Employee(1, "John", "Doe", "john@test.com");
        Employee employee2 = new Employee(2, "Tom", "Will", "tom@example.com");
        Employee employee3 = new Employee(3, "Susan", "Mary", "susan@gmail.com");

        this.employees = new ArrayList<>();
        this.employees.add(employee1);
        this.employees.add(employee2);
        this.employees.add(employee3);
    }

    @GetMapping("/list")
    public String getEmployees(Model model) {
        model.addAttribute("employees", this.employees);
        return "employee";
    }

}
