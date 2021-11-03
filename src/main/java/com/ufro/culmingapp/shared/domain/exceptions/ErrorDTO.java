package com.ufro.culmingapp.shared.domain.exceptions;

public class ErrorDTO {

    private String cause;

    public ErrorDTO(String causa) {
        this.cause = causa;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

}
