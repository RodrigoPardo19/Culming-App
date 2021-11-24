package com.ufro.culmingapp.assistance.application.DTOs;

public class AssistancesStatusDTO {

    Long assistanceId;
    Boolean isPresent;

    public AssistancesStatusDTO(Long assistanceId, Boolean isPresent) {
        this.assistanceId = assistanceId;
        this.isPresent = isPresent;
    }

    public Long getAssistanceId() {
        return this.assistanceId;
    }

    public void setAssistanceId(Long assistanceId) {
        this.assistanceId = assistanceId;
    }

    public Boolean isPresent() {
        return this.isPresent;
    }

    public void setIsPresent(Boolean isPresent) {
        this.isPresent = isPresent;
    }

}
