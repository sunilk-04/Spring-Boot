package com.prominentpixel.springboot.cruddemo.dao;

import com.prominentpixel.springboot.cruddemo.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOJPAImplementation implements EmployeeDAO {

    private EntityManager entityManager;

    public EmployeeDAOJPAImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        return this.entityManager.createQuery("from Employee", Employee.class).getResultList();
    }

    @Override
    public Employee find(int id) {
        return this.entityManager.find(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {
        Employee tempEmployee = this.entityManager.merge(employee);
        employee.setId(tempEmployee.getId());
    }

    @Override
    public void delete(int id) {
        this.entityManager.remove(this.find(id));
    }
}
