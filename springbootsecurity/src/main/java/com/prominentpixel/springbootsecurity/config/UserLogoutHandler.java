package com.prominentpixel.springbootsecurity.config;

import com.prominentpixel.springbootsecurity.service.LoginLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserLogoutHandler implements LogoutHandler {

    @Autowired
    private LoginLogoutService loginLogoutService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        this.loginLogoutService.save("logout");
    }
}
