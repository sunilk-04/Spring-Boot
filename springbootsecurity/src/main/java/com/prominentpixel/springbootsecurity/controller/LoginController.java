package com.prominentpixel.springbootsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String show() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String deny() {
        return "access-denied";
    }

}
