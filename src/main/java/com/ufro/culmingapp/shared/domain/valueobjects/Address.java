package com.ufro.culmingapp.shared.domain.valueobjects;

import javax.persistence.Embeddable;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;

@Embeddable
public class Address {

    private String address;

    public Address() {
        // Used only for spring
    }

    public Address(String address) throws NullFieldNotPermitted {
        if (!isValid(address)) {
            throw new NullFieldNotPermitted("Address");
        }

        this.address = address;
    }

    public Boolean isValid(String value) {
        if (value == null || value.isBlank()) {
            return false;
        }
        return true;
    }

    public String getAddress() {
        return this.address;
    }

}
