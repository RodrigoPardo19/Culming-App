package com.ufro.culmingapp.evaluation.domain;

import javax.persistence.Embeddable;

import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;

@Embeddable
public class EvaluationDescription {

    private String description;

    public EvaluationDescription() {
        // Used only for spring
    }

    public EvaluationDescription(String description) throws NullFieldNotPermitted {
        if (isNull(description)) {
            throw new NullFieldNotPermitted("Description");
        }
        this.description = description;
    }

    private Boolean isNull(String description) {
        if (description == null || description.isBlank()) {
            return true;
        }
        return false;
    }

    public String getDescription() {
        return this.description;
    }
}
