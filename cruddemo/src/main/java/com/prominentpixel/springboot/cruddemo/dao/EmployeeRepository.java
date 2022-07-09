package com.prominentpixel.springboot.cruddemo.dao;

import com.prominentpixel.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
