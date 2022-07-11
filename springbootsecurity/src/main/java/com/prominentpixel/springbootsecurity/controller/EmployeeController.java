package com.prominentpixel.springbootsecurity.controller;

import com.prominentpixel.springbootsecurity.entity.Employee;
import com.prominentpixel.springbootsecurity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public String getEmployees(Model model) {
        model.addAttribute("employees", this.employeeService.findAll());
        return "employees/list-employees";
    }

    @GetMapping("/showForm")
    public String show(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") Employee employee) {
        this.employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("employeeId") int id, Model model) {
        model.addAttribute("employee", this.employeeService.findById(id));
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id) {
        this.employeeService.deleteById(id);
        return "redirect:/employees/list";
    }
}
