package com.ufro.culmingapp.evaluation.application.DTOs;

import com.ufro.culmingapp.evaluation.domain.EvaluationDate;

public class EvaluationDTO {

    private Long id;
    private String description;
    private String qualificationDate;

    public EvaluationDTO(Long id, String description, EvaluationDate qualificationDate) {
        this.id = id;
        this.description = description;
        this.qualificationDate = qualificationDate.getStringEvaluationDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQualificationDate() {
        return qualificationDate;
    }

    public void setQualificationDate(String qualificationDate) {
        this.qualificationDate = qualificationDate;
    }

}
