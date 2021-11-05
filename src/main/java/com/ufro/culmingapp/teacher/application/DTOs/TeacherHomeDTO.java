package com.ufro.culmingapp.teacher.application.DTOs;

import java.time.LocalDate;

public class TeacherHomeDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondSurname;
    private LocalDate todayDate;
    private String description;

    public TeacherHomeDTO(Long id, String firstName, String middleName, String lastName, String secondSurname) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondSurname = secondSurname;
        this.todayDate = setTodayDate();
        this.description = setDescription();
    }

    private LocalDate setTodayDate() {
        return LocalDate.now();
    }

    private String setDescription() {
        return "Bienvenido/a a la plataforma de gestión del colegio CulmingUp. Usa la barra lateral/Side Bar que se encuentra a la izquierda "
                + "para acceder a todas las funcionalidades";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public LocalDate getTodayDate() {
        return todayDate;
    }

    public String getDescription() {
        return description;
    }

}
