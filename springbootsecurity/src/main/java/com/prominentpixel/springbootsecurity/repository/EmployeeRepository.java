package com.prominentpixel.springbootsecurity.repository;

import com.prominentpixel.springbootsecurity.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Add below to sort by last name ascending
    /*
        Spring data JPA will parse the method name
        Looks for a specific format and pattern and then
        creates appropriate query behind the scenes
     */
    public List<Employee> findAllByOrderByLastNameAsc();

}
