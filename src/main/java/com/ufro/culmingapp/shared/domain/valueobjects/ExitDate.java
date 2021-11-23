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
public class ExitDate {

    @Column(name = "exit_date")
    private LocalDate exitDate;

    /**
     * ISO_LOCAL_DATE is equivalent to 'yyyy-MM-dd'
     */
    @Transient
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public ExitDate() {
        // Used only for the spring
    }

    public ExitDate(String exitDate) throws NullFieldNotPermitted, ParseException {
        if (isNull(exitDate)) {
            throw new NullFieldNotPermitted("ExitDate");
        }
        if (!isValid(exitDate)) {
            throw new DateTimeParseException("Bad format yyyy-MM-dd", exitDate, 0);
        }
        this.exitDate = LocalDate.parse(exitDate, this.formatter);
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

    public LocalDate getExitDate() {
        return this.exitDate;
    }

    // Validation for date between start and end pending
}
