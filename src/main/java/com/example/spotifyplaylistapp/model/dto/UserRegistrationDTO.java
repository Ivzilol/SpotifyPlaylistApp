package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.validation.annotation.UniqueEmail;
import com.example.spotifyplaylistapp.validation.annotation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistrationDTO {

    @UniqueUsername
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters")
    @NotNull
    private String username;
    @UniqueEmail
    @Email(message = "Email cannot be empty")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    @NotNull
    private String password;
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters")
    @NotNull
    private String confirmPassword;

    public UserRegistrationDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
