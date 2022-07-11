package com.prominentpixel.springbootsecurity.dao;

import com.prominentpixel.springbootsecurity.entity.User;

public interface UserDAO {

    User findByUserName(String userName);

    void save(User user);

}
