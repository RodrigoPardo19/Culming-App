package com.ufro.culmingapp.teacher.application.DTOs;

public class TeacherAuthenticationDTO {

    private String email;
    private String password;

    public TeacherAuthenticationDTO() {
    }

    public TeacherAuthenticationDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TeacherAuthenticationDTO email(String email) {
        setEmail(email);
        return this;
    }

    public TeacherAuthenticationDTO password(String password) {
        setPassword(password);
        return this;
    }

}
