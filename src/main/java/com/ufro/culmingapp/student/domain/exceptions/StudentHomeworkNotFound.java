package com.ufro.culmingapp.student.domain.exceptions;

public class StudentHomeworkNotFound extends Exception {

    public StudentHomeworkNotFound(Long studentId, Long homeworkId) {
        super("Student's homework has not been found, please check the studentId: "
                + studentId + " or homeworkId: " + homeworkId);
    }
}
