package com.ufro.culmingapp.student.domain;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;

@Embeddable
public class StudentGraduationDate {

    @Column(name = "graduation_date")
    private LocalDate graduationDate;

    /**
     * ISO_LOCAL_DATE is equivalent to 'yyyy-MM-dd'
     */
    @Transient
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public StudentGraduationDate() {
        // Used only for the spring
    }

    public StudentGraduationDate(String graduationDate)
            throws NullFieldNotPermitted, ParseException {
        if (isNull(graduationDate)) {
            throw new NullFieldNotPermitted("GraduationDate");
        }
        if (!isValid(graduationDate)) {
            throw new DateTimeParseException("Bad format yyyy-MM-dd", graduationDate, 0);
        }
        this.graduationDate = LocalDate.parse(graduationDate, this.formatter);
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

    public LocalDate getGraduationDate() {
        return this.graduationDate;
    }

    // Validation for date between start and end pending
}
