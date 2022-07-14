package com.prominentpixel.jpawithdynamicquery.service;

import com.prominentpixel.jpawithdynamicquery.entity.Student;
import com.prominentpixel.jpawithdynamicquery.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    @Override
    public List<Student> getAll() {
        return this.studentRepository.getAll();
    }

    @Transactional
    @Override
    public Student getById(int id) {
        return this.studentRepository.getById(id);
    }

    @Transactional
    @Override
    public List<Student> getByIds(List<Integer> ids, String sortBy) {
        return this.studentRepository.getByIds(ids, Sort.by(sortBy));
    }

    @Transactional
    @Override
    public void deactivate(int id) {
        this.studentRepository.deactivate(id);
    }

    @Transactional
    @Override
    public void activate(int id) {
        this.studentRepository.activate(id);
    }

    @Transactional
    @Override
    public void insert(String name, String email, String city) {
        this.studentRepository.insert(name, email, city);
    }

    @Override
    public List<Integer> getActiveIds() {
        return this.studentRepository.getActiveIds();
    }
}
