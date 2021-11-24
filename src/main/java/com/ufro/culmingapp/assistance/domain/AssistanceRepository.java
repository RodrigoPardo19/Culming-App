package com.ufro.culmingapp.assistance.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ufro.culmingapp.assistance.application.DTOs.AssistanceDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssistanceRepository extends JpaRepository<Assistance, Long> {

    @Query("SELECT new com.ufro.culmingapp.assistance.application.DTOs.AssistanceDTO(a.id, "
           +"a.date) FROM Assistance a WHERE a.course.id = :courseId "
           +"AND a.subject.id = :subjectId AND a.year.year = 2021 AND a.date.date " 
           +"BETWEEN :start AND :end ORDER BY a.id")
    Optional<List<AssistanceDTO>> fetchAssistancesOfASubjectInACourseByMonth(
            @Param("courseId") Integer courseId, @Param("subjectId") Integer subjectId, 
            @Param("start") LocalDate start, @Param("end") LocalDate end);

}
