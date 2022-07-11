package com.prominentpixel.springbootsecurity.controller;

import com.prominentpixel.springbootsecurity.entity.User;
import com.prominentpixel.springbootsecurity.service.UserService;
import com.prominentpixel.springbootsecurity.user.CRMUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("userOfCRM", new CRMUser());
        return "register";
    }

    @PostMapping("/registerNewUser")
    public String registerNewUser(@Valid @ModelAttribute("userOfCRM") CRMUser userOfCRM, BindingResult bindingResult, Model model) {
        this.logger.info("Registering user : " + userOfCRM.getUserName());
        if (bindingResult.hasErrors()) {
            return "register";
        }
        User existingUser = this.userService.findByUserName(userOfCRM.getUserName());
        if (existingUser != null) {
            model.addAttribute("userOfCRM", new CRMUser());
            model.addAttribute("registrationError", "Username already exists.");
            this.logger.warning("Username already exists.");
            return "register";
        }
        this.userService.save(userOfCRM);
        this.logger.info("Successfully created user : " + userOfCRM.getUserName());
        return "registration-confirmation";
    }

}
