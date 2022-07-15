package com.prominentpixel.gsondemo.entity;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Student {

    private int id;

    private String name;

    private String city;

    public Student(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public static Student getStudentById(int studentId, List<Student> students) {
        List<Student> result = students.stream().filter(student -> student.id == studentId).collect(Collectors.toList());
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null;
    }
}