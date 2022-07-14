package com.prominentpixel.springbootsecurity.service;

import com.prominentpixel.springbootsecurity.entity.LoginLogoutHistory;
import com.prominentpixel.springbootsecurity.repository.LoginLogoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class LoginLogoutServiceImplementation implements LoginLogoutService {

    @Autowired
    private LoginLogoutRepository loginLogoutRepository;

    @Override
    public void save(String operation) {
        String userName;
        LoginLogoutHistory loginLogoutHistory = new LoginLogoutHistory();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        loginLogoutHistory.setUserName(userName);
        loginLogoutHistory.setTime(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(Calendar.getInstance().getTime()));
        loginLogoutHistory.setOperation(operation);
        this.loginLogoutRepository.save(loginLogoutHistory);
    }

    @Override
    public List<LoginLogoutHistory> findAll() {
        return this.loginLogoutRepository.findAll();
    }
}
