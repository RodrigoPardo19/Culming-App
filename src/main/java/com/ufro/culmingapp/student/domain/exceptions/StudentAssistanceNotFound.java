package com.ufro.culmingapp.student.domain.exceptions;

public class StudentAssistanceNotFound extends Exception {

    public StudentAssistanceNotFound(Long studentId, Long assistanceId) {
        super("Student's assistance has not been found, please check the studentId: "
                + studentId + " or assistanceId: " + assistanceId);
    }
}
