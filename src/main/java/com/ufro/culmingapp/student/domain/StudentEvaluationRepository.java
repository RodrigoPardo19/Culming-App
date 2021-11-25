package com.ufro.culmingapp.student.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEvaluationRepository extends JpaRepository<StudentEvaluation, Long> {

    Optional<StudentEvaluation> findByStudentIdAndEvaluationId(Long studentId, Long evaluationId);

}
