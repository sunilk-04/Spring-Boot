package com.prominentpixel.springbootsecurity.controller;

import com.prominentpixel.springbootsecurity.entity.LoginLogoutHistory;
import com.prominentpixel.springbootsecurity.service.LoginLogoutService;
import com.prominentpixel.springbootsecurity.user.ActiveUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    private ActiveUserStore activeUserStore;

    @Autowired
    private LoginLogoutService loginLogoutService;

    @GetMapping("/loggedUsers")
    public String getLoggedUsers(Locale locale, Model model) {
        model.addAttribute("loggedUsers", this.activeUserStore.getUsers());
        return "users";
    }

    @GetMapping("/history")
    public String getLoginLogoutHistory(Model model) {
        model.addAttribute("history", this.loginLogoutService.findAll());
        return "login-logout-history";
    }
}
