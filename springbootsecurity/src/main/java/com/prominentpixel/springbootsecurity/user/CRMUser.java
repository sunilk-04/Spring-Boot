package com.prominentpixel.springbootsecurity.user;


import com.prominentpixel.springbootsecurity.validation.Email;
import com.prominentpixel.springbootsecurity.validation.Match;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Match.List({
        @Match(first = "password", second = "confirmPassword", message = "Password and confirm password must match.")
})
public class CRMUser {

    @NotNull(message = "Username is required.")
    @Size(min = 3, message = "Username should be at least 3 characters long.")
    private String userName;

    @NotNull(message = "Password is required.")
    @Size(min = 5, message = "Password should be at least 5 characters long.")
    private String password;

    @NotNull(message = "Password confirmation is required.")
    @Size(min = 5, message = "Password should be at least 5 characters long.")
    private String confirmPassword;

    @NotNull(message = "Firstname is required.")
    @Size(min = 2, message = "Firstname should be at least 2 characters long.")
    private String firstName;

    @NotNull(message = "Lastname is required.")
    @Size(min = 2, message = "Lastname should be at least 2 characters long.")
    private String lastName;

    @Email
    @NotNull(message = "Email is required.")
    private String email;

    public CRMUser() {

    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
