package com.ufro.culmingapp.shared.domain.valueobjects;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.domain.exceptions.WrongEmailFormat;

@Embeddable
public class Email {

    private String email;

    @Transient
    private final String REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public Email() {
        // Used only for spring
    }

    public Email(String email) throws NullFieldNotPermitted, WrongEmailFormat {
        if (isNull(email)) {
            throw new NullFieldNotPermitted("Email");
        }
        if (!isValid(email)) {
            throw new WrongEmailFormat(email);
        }
        this.email = email;
    }

    private Boolean isNull(String email) {
        return (email == null || email.isBlank()) ? true : false;
    }

    private Boolean isValid(String email) {
        return email.matches(REGEX) ? true : false;
    }

    public String getEmail() {
        return this.email;
    }

}
