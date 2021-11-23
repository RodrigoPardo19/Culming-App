package com.ufro.culmingapp.homework.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ufro.culmingapp.homework.application.DTOs.HomeworkDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {

    @Query("SELECT new com.ufro.culmingapp.homework.application.DTOs.HomeworkDTO(h.id, "
           +"h.instruction.instruction, h.deadline) FROM Homework h WHERE h.course.id = :courseId "
           +"AND h.subject.id = :subjectId AND h.year.year = 2021 AND h.deadline.deadline " 
           +"BETWEEN :start AND :end ORDER BY h.id")
    Optional<List<HomeworkDTO>> fetchHomeworksOfASubjectInACourseByMonth(
            @Param("courseId") Integer courseId, @Param("subjectId") Integer subjectId, 
            @Param("start") LocalDate start, @Param("end") LocalDate end);

}
