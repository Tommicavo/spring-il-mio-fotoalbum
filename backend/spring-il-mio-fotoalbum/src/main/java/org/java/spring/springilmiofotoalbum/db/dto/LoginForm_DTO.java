package org.java.spring.springilmiofotoalbum.db.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginForm_DTO {

    @NotBlank(message = "Field 'username' must not be empty")
    private String username;

    @NotBlank(message = "Field 'password' must not be empty")
    private String password;

    public LoginForm_DTO() {
    }

    public LoginForm_DTO(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
