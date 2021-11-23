package com.ufro.culmingapp.studentassistance.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentAssistanceId implements Serializable {

    @Column(name = "student_id")
    protected Long studentId;

    @Column(name = "assistance_id")
    protected Long assistanceId;

    public StudentAssistanceId() {
        // Used only for spring
    }

    public StudentAssistanceId(Long studentId, Long assistanceId) {
        this.studentId = studentId;
        this.assistanceId = assistanceId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((studentId == null) ? 0 : studentId.hashCode());
        result = prime * result
                + ((assistanceId == null) ? 0 : assistanceId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        StudentAssistanceId other = (StudentAssistanceId) obj;

        if (studentId == null) {
            if (other.studentId != null)
                return false;
        } else if (!studentId.equals(other.studentId))
            return false;

        if (assistanceId == null) {
            if (other.assistanceId != null)
                return false;
        } else if (!assistanceId.equals(other.assistanceId))
            return false;
        return true;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getAssistanceId() {
        return assistanceId;
    }

    public void setAssistanceId(Long assistanceId) {
        this.assistanceId = assistanceId;
    }
}
