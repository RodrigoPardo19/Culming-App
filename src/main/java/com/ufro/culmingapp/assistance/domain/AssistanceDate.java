package com.ufro.culmingapp.assistance.domain;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;

@Embeddable
public class AssistanceDate {

    @Column(name = "date_of_assistance")
    private LocalDate date;

    /**
     * ISO_LOCAL_DATE is equivalent to 'yyyy-MM-dd'
     */
    @Transient
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public AssistanceDate() {
        // Used only for the spring
    }

    public AssistanceDate(String date)
            throws NullFieldNotPermitted, ParseException {
        if (isNull(date)) {
            throw new NullFieldNotPermitted("AssistanceDate");
        }
        if (!isValid(date)) {
            throw new DateTimeParseException("Bad format yyyy-MM-dd", date, 0);
        }
        this.date = LocalDate.parse(date, this.formatter);
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

    public LocalDate getDate() {
        return this.date;
    }

    public String getStringDate() {
        return this.formatter.format(date);
    }

}
