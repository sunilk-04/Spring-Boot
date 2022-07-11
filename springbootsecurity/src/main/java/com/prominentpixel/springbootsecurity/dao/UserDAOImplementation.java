package com.prominentpixel.springbootsecurity.dao;

import com.prominentpixel.springbootsecurity.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserDAOImplementation implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User findByUserName(String userName) {
        Query<User> query = this.entityManager.unwrap(Session.class).createQuery("from User where userName=:uName", User.class);
        query.setParameter("uName", userName);
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User user) {
        this.entityManager.unwrap(Session.class).saveOrUpdate(user);
    }
}
