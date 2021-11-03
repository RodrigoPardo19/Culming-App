package com.ufro.culmingapp.shared.domain.valueobjects;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;

@Embeddable
public class DateOfBirth {

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    /**
     * ISO_LOCAL_DATE is equivalent to 'yyyy-MM-dd'
     */
    @Transient
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public DateOfBirth() {
        // Used only for the spring
    }

    public DateOfBirth(String dateOfBirth) throws NullFieldNotPermitted, ParseException {
        if (isNull(dateOfBirth)) {
            throw new NullFieldNotPermitted("DateOfBirth");
        }
        if (!isValid(dateOfBirth)) {
            throw new DateTimeParseException("bad format yyyy-MM-dd", dateOfBirth, 0);
        }
        this.dateOfBirth = LocalDate.parse(dateOfBirth, this.formatter);
    }

    private Boolean isNull(String date) {
        if (date == null || date.isBlank()) {
            return true;
        }
        return false;
    }

    private Boolean isValid(String date) {
        try {
            LocalDate.parse(date, this.formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getStringDateOfBirth() {
        return this.formatter.format(dateOfBirth);
    }

    // Validation for date between start and end pending
    // Validation for right date format "yyyy-MM-dd" pending
}
