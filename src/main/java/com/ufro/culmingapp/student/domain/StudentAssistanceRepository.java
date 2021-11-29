package com.ufro.culmingapp.student.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentAssistanceRepository
        extends JpaRepository<StudentAssistance, StudentAssistanceId> {

    Optional<StudentAssistance> findByStudentIdAndAssistanceId(Long studentId, Long assistanceId);
}
