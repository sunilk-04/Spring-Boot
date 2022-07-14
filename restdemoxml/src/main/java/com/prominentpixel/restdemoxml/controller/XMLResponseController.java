package com.prominentpixel.restdemoxml.controller;

import com.prominentpixel.restdemoxml.entity.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = "application/xml")
public class XMLResponseController {

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
    public ResponseEntity<List<Student>> get() {
        return new ResponseEntity<>(this.students, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getById(@PathVariable int id) {
        return new ResponseEntity<>(Student.getStudentById(id, this.students), new HttpHeaders(), HttpStatus.CREATED);
    }
}
