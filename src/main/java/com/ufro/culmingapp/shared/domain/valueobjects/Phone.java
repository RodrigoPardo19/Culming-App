package com.ufro.culmingapp.shared.domain.valueobjects;

import javax.persistence.Embeddable;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.domain.exceptions.WrongLength;

@Embeddable
public class Phone {

    private String phone;

    public Phone() {
        // Used only for spring
    }

    public Phone(String phone) throws NullFieldNotPermitted, WrongLength {
        if (isNull(phone)) {
            throw new NullFieldNotPermitted("Phone");
        }
        if (!hasRightLength(phone)) {
            throw new WrongLength("Phone, must have 9 numbers");
        }
        if (!isNumeric(phone)) {
            throw new NumberFormatException("Phone must be a number format");
        }
        this.phone = phone;
    }

    private Boolean isNull(String value) {
        if (value == null || value.isBlank()) {
            return true;
        }
        return false;
    }

    private Boolean hasRightLength(String value) {
        if (value.length() > 9 || value.length() < 9) {
            return false;
        }
        return true;
    }

    private Boolean isNumeric(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public String getPhone() {
        return this.phone;
    }

}
