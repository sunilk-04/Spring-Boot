package com.prominentpixel.springbootsecurity.service;

import com.prominentpixel.springbootsecurity.entity.User;
import com.prominentpixel.springbootsecurity.user.CRMUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(CRMUser user);

}
