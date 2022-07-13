package com.prominentpixel.flywaydemo.repository;

import com.prominentpixel.flywaydemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
