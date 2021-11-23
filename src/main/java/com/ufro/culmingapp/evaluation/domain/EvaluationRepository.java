package com.ufro.culmingapp.evaluation.domain;

import java.util.List;
import java.util.Optional;

import com.ufro.culmingapp.evaluation.application.DTOs.EvaluationDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query("SELECT new com.ufro.culmingapp.evaluation.application.DTOs.EvaluationDTO(e.id, "
           + "e.description.description, e.date) FROM Evaluation e WHERE "
           + "e.course.id = :courseId AND e.subject.id = :subjectId AND e.year.year = 2021 AND e.type.id = 1")
    Optional<List<EvaluationDTO>> fetchEvaluationOfASubjectInACourse(
            @Param("courseId") Integer courseId, @Param("subjectId") Integer subjectId);

    @Query("SELECT new com.ufro.culmingapp.evaluation.application.DTOs.EvaluationDTO(e.id, "
           + "e.description.description, e.date) FROM Evaluation e WHERE "
           + "e.course.id = :courseId AND e.subject.id = :subjectId AND e.year.year = 2021 AND e.type.id = 2")
    Optional<List<EvaluationDTO>> fetchWorkshopsOfASubjectInACourse(
            @Param("courseId") Integer courseId, @Param("subjectId") Integer subjectId);

}
