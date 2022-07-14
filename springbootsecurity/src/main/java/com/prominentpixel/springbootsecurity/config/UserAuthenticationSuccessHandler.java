package com.prominentpixel.springbootsecurity.config;

import com.prominentpixel.springbootsecurity.entity.User;
import com.prominentpixel.springbootsecurity.service.LoginLogoutService;
import com.prominentpixel.springbootsecurity.service.UserService;
import com.prominentpixel.springbootsecurity.user.ActiveUserStore;
import com.prominentpixel.springbootsecurity.user.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private ActiveUserStore activeUserStore;

    @Autowired
    private LoginLogoutService loginLogoutService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = this.userService.findByUserName(authentication.getName());
        if (session != null) {
            LoggedUser loggedUser = new LoggedUser();
            loggedUser.setUsername(authentication.getName());
            loggedUser.setActiveUserStore(this.activeUserStore);
            session.setAttribute("user", user);
            session.setAttribute("loggedUser", loggedUser);
            this.loginLogoutService.save("login");
        }
        response.sendRedirect(request.getContextPath() + "/");
    }
}
