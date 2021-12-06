package com.ufro.culmingapp.shared.domain.valueobjects;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Embeddable
public class EnrollmentDate {

    @Column(name = "enrollment_Date")
    private LocalDate enrollmentDate;

    /**
     * ISO_LOCAL_DATE is equivalent to 'yyyy-MM-dd'
     */
    @Transient
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public EnrollmentDate() {
        // Used only for the spring
    }

    public EnrollmentDate(String enrollmentDate) throws NullFieldNotPermitted, ParseException {
        if (isNull(enrollmentDate)) {
            throw new NullFieldNotPermitted("EnrollmentDate");
        }
        if (!isValid(enrollmentDate)) {
            throw new DateTimeParseException("Bad format yyyy-MM-dd", enrollmentDate, 0);
        }
        this.enrollmentDate = LocalDate.parse(enrollmentDate, this.formatter);
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

    public LocalDate getEnrollmentDate() {
        return this.enrollmentDate;
    }

    public String getStringEnrollmentDate() {
        return this.formatter.format(enrollmentDate);
    }

    // Validation for date between start and end pending
}
