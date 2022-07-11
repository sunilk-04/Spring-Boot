package com.prominentpixel.springboot.cruddemo.dao;

import com.prominentpixel.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="members") // To use custom resource name (Here : '/employees' will be replaced with '/members')
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
