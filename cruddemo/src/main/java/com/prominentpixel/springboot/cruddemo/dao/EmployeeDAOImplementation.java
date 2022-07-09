package com.prominentpixel.springboot.cruddemo.dao;

import com.prominentpixel.springboot.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO {

    private EntityManager entityManager;

    public EmployeeDAOImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        return this.entityManager.unwrap(Session.class).createQuery("from Employee", Employee.class).getResultList();
    }

    @Override
    public Employee find(int id) {
        return this.entityManager.unwrap(Session.class).get(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {
        this.entityManager.unwrap(Session.class).saveOrUpdate(employee);
    }

    @Override
    public void delete(int id) {
        this.entityManager.unwrap(Session.class).delete(this.find(id));
    }
}
