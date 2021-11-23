package com.ufro.culmingapp.evaluation.domain;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;

@Embeddable
public class EvaluationDate {

    @Column(name = "qualification_date")
    private LocalDate evaluationDate;

    /**
     * ISO_LOCAL_DATE is equivalent to 'yyyy-MM-dd'
     */
    @Transient
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public EvaluationDate() {
        // Used only for the spring
    }

    public EvaluationDate(String evaluationDate)
            throws NullFieldNotPermitted, ParseException {
        if (isNull(evaluationDate)) {
            throw new NullFieldNotPermitted("EvaluationDate");
        }
        if (!isValid(evaluationDate)) {
            throw new DateTimeParseException("Bad format yyyy-MM-dd", evaluationDate, 0);
        }
        this.evaluationDate = LocalDate.parse(evaluationDate, this.formatter);
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

    public LocalDate getEvaluationDate() {
        return this.evaluationDate;
    }

   public String getStringEvaluationDate() {
       return this.formatter.format(evaluationDate);
   }

}
