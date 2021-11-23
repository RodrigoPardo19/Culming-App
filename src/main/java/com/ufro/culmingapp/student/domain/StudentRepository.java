package com.ufro.culmingapp.student.domain;

import java.util.List;
import java.util.Optional;

import com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT new com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO(s.id, "
           +"s.fullName.firstName, s.fullName.lastName, e.id, st.grade.grade) FROM Evaluation e "
           +"JOIN e.students st JOIN st.student s WHERE e.course.id = :courseId AND "
           +"e.subject.id = :subjectId AND e.year.year = 2021 AND e.type.id = 1 ORDER BY s.id")
    Optional<List<StudentWithEvaluationDTO>> fetchEvaluationsOfStudentsOfASubjectInACourse(
            @Param("courseId") Integer courseId, @Param("subjectId") Integer subjectId);

    @Query("SELECT new com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO(s.id, "
           +"s.fullName.firstName, s.fullName.lastName, e.id, st.grade.grade) FROM Evaluation e "
           +"JOIN e.students st JOIN st.student s WHERE e.course.id = :courseId AND "
           +"e.subject.id = :subjectId AND e.year.year = 2021 AND e.type.id = 2 ORDER BY s.id")
    Optional<List<StudentWithEvaluationDTO>> fetchWorkshopsOfStudentsOfASubjectInACourse(
            @Param("courseId") Integer courseId, @Param("subjectId") Integer subjectId);
}
