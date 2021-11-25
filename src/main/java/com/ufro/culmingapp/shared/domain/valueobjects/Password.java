package com.ufro.culmingapp.shared.domain.valueobjects;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.domain.exceptions.WrongPasswordFormat;

@Embeddable
public class Password {

    private String password;

    @Transient
    private final String REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

    public Password() {
        // Used only for spring
    }

    public Password(String password) throws NullFieldNotPermitted, WrongPasswordFormat {
        if (isNull(password)) {
            throw new NullFieldNotPermitted("Password");
        }
        if (!isValid(password)) {
            throw new WrongPasswordFormat(password);
        }
        this.password = password;
    }

    private Boolean isNull(String password) {
        return (password == null || password.isBlank()) ? true : false;
    }

    private Boolean isValid(String password) {
        return password.matches(REGEX) ? true : false;
    }

    public String getPassword() {
        return this.password;
    }
}
