package com.ufro.culmingapp.studentassistance.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAssistanceRepository
        extends JpaRepository<StudentAssistance, StudentAssistanceId> {

}
