package com.ufro.culmingapp.homework.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;

@Embeddable
public class HomeworkDeadline {

    private LocalDate deadline;

    /**
     * ISO_LOCAL_DATE is equivalent to 'yyyy-MM-dd'
     */
    @Transient
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public HomeworkDeadline() {
        // Used only for the spring
    }

    public HomeworkDeadline(String deadline)
            throws NullFieldNotPermitted, DateTimeParseException {
        if (isNull(deadline)) {
            throw new NullFieldNotPermitted("EvaluationDate");
        }
        if (!isValid(deadline)) {
            throw new DateTimeParseException("Bad format yyyy-MM-dd", deadline, 0);
        }
        this.deadline = LocalDate.parse(deadline, this.formatter);
    }

    private Boolean isNull(String deadline) {
        if (deadline == null || deadline.isBlank()) {
            return true;
        }
        return false;
    }

    private Boolean isValid(String deadline) {
        try {
            LocalDate.parse(deadline, this.formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public String getStringDeadLine() {
        return this.formatter.format(deadline);
    }

}
