package com.prominentpixel.springbootsecurity.dao;

import com.prominentpixel.springbootsecurity.entity.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RoleDAOImplementation implements RoleDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role findRoleByName(String name) {
        Query<Role> query = this.entityManager.unwrap(Session.class).createQuery("from Role where name=:roleName", Role.class);
        query.setParameter("roleName", name);
        Role role = null;

        try {
            role = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }
}
