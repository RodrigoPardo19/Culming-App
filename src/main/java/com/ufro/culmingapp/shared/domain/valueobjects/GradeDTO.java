package com.ufro.culmingapp.shared.domain.valueobjects;

public class GradeDTO {

    private Long evaluationId;
    private Double grade;

    public GradeDTO(Long evaluationId, Double grade) {
        this.evaluationId = evaluationId;
        this.grade = grade;
    }

    public Long getEvaluationId() {
        return evaluationId;
    }
    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }
    public Double getGrade() {
        return grade;
    }
    public void setGrade(Double grade) {
        this.grade = grade;
    }

}
