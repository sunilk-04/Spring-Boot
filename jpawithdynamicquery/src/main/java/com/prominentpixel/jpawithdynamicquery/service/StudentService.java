package com.prominentpixel.jpawithdynamicquery.service;


import com.prominentpixel.jpawithdynamicquery.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll();

    Student getById(int id);

    List<Student> getByIds(List<Integer> ids, String sortBy);

    void deactivate(int id);

    void activate(int id);

    void insert(String name, String email, String city);

    List<Integer> getActiveIds();
}
