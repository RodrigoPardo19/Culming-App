package com.ufro.culmingapp.administrator.application.DTOs;

import java.time.LocalDate;

import com.ufro.culmingapp.shared.domain.valueobjects.FullName;

public class AdminHomeDTO {

    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondSurname;
    private LocalDate todayDate;
    private String description;

    public AdminHomeDTO(Long id, FullName fullName) {
        this.id = id;
        this.firstName = fullName.getFirstName();
        this.middleName = fullName.getMiddleName();
        this.lastName = fullName.getLastName();
        this.secondSurname = fullName.getSecondSurname();
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
