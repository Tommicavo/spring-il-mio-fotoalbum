package org.java.spring.springilmiofotoalbum.auth.db.dto;

public class LoginForm {

    private String username;
    private String password;

    public LoginForm() {
    }

    public LoginForm(String username, String password) {
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
