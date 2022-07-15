package com.prominentpixel.client.controller;

import com.prominentpixel.client.entity.User;
import com.prominentpixel.client.entity.VerificationToken;
import com.prominentpixel.client.event.RegistrationCompleteEvent;
import com.prominentpixel.client.model.PasswordModel;
import com.prominentpixel.client.model.UserModel;
import com.prominentpixel.client.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        User user = this.userService.registerUser(userModel);
        this.publisher.publishEvent(new RegistrationCompleteEvent(user, this.applicationUrl(request)));
        return "Success.";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = this.userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid.")) {
            return "User has been verified successfully.";
        }
        return "Bad user.";
    }


    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request) {
        VerificationToken verificationToken = this.userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        this.resendVerificationTokenMail(user, this.applicationUrl(request), verificationToken);
        return "Verification link has been sent.";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {
        User user = this.userService.findUserByEmail(passwordModel.getEmail());
        String url = "";
        if (user != null) {
            String token = UUID.randomUUID().toString();
            this.userService.createPasswordResetTokenForUser(user, token);
            url = this.passwordResetTokenMail(user, this.applicationUrl(request), token);
        }
        return url;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token, @RequestBody PasswordModel passwordModel) {
        String result = this.userService.validatePasswordResetToken(token);
        if (!result.equalsIgnoreCase("valid.")) {
            return "Invalid token.";
        }
        Optional<User> user = this.userService.getUserByPasswordResetToken(token);
        if (user.isPresent()) {
            this.userService.changePassword(user.get(), passwordModel.getNewPassword());
            return "Password has been reset successfully.";
        } else {
            return "Invalid token.";
        }
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordModel passwordModel) {
        User user = this.userService.findUserByEmail(passwordModel.getEmail());
        if (!this.userService.checkIfValidOldPassword(user, passwordModel.getOldPassword())) {
            return "Invalid old password.";
        }
        //Save New Password
        this.userService.changePassword(user, passwordModel.getNewPassword());
        return "Password has been changed successfully.";
    }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url = applicationUrl + "/savePassword?token=" + token;

        //sendVerificationEmail()
        log.info("Click the link to reset your password: {}", url);
        return url;
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
        String url = applicationUrl + "/verifyRegistration?token=" + verificationToken.getToken();

        //sendVerificationEmail()
        log.info("Click the link to verify your account: {}", url);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
