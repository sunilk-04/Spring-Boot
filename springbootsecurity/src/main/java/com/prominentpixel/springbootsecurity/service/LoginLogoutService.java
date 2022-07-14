package com.prominentpixel.springbootsecurity.service;

import com.prominentpixel.springbootsecurity.entity.LoginLogoutHistory;

import java.util.List;

public interface LoginLogoutService {

    void save(String operation);
    List<LoginLogoutHistory> findAll();

}
