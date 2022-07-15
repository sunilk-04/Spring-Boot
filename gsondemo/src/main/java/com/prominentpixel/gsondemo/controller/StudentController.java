package com.prominentpixel.gsondemo.controller;

import com.prominentpixel.gsondemo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class StudentController {

    private List<Student> students;

    @PostConstruct
    private void load() {
        this.students = new ArrayList<>();
        this.students.add(new Student(1, "John", "London"));
        this.students.add(new Student(2, "Tom", "New York"));
        this.students.add(new Student(3, "David", "Sydney"));
        this.students.add(new Student(4, "Abraham", "Dubai"));
        this.students.add(new Student(5, "Steve", "Melbourne"));
    }

    @GetMapping("/students")
    public List<Student> get() {
        return this.students;
    }

    @GetMapping("/students/{id}")
    public Student getById(@PathVariable int id) {
        return Student.getStudentById(id, this.students);
    }
}
