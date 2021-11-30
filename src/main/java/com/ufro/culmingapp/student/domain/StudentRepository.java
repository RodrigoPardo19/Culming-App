package com.ufro.culmingapp.student.domain;

import com.ufro.culmingapp.student.application.DTOs.StudentWithAssistanceDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithHomeworkDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT new com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO(s.id, "
            + "s.fullName.firstName, s.fullName.lastName, e.id, st.grade.grade) FROM Evaluation e "
            + "JOIN e.students st JOIN st.student s WHERE e.course.id = :courseId AND "
            + "e.subject.id = :subjectId AND e.year.year = 2021 AND e.type.id = 1 ORDER BY s.id")
    Optional<List<StudentWithEvaluationDTO>> fetchEvaluationsOfStudentsOfASubjectInACourse(
            @Param("courseId") Integer courseId, @Param("subjectId") Integer subjectId);


    @Query("SELECT new com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO(s.id, "
            + "s.fullName.firstName, s.fullName.lastName, e.id, st.grade.grade) FROM Evaluation e "
            + "JOIN e.students st JOIN st.student s WHERE e.course.id = :courseId AND "
            + "e.subject.id = :subjectId AND s.id = :studentId AND e.year.year = 2021 AND e.type.id = 1")
    Optional<List<StudentWithEvaluationDTO>> fetchEvaluationOfStudentOfASubjectInACourse(
            @Param("courseId") Integer courseId, @Param("subjectId") Integer subjectId,
            @Param("studentId") Long studentId);

    @Query("SELECT new com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO(s.id, "
            + "s.fullName.firstName, s.fullName.lastName, e.id, st.grade.grade) FROM Evaluation e "
            + "JOIN e.students st JOIN st.student s WHERE e.course.id = :courseId AND "
            + "e.subject.id = :subjectId AND s.id = :studentId AND e.year.year = 2021 AND e.type.id = 2")
    Optional<List<StudentWithEvaluationDTO>> fetchWorkshopsOfStudentOfASubjectInACourse(
            @Param("courseId") Integer courseId, @Param("subjectId") Integer subjectId,
            @Param("studentId") Long studentId);

    @Query("SELECT new com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO(s.id, "
            + "s.fullName.firstName, s.fullName.lastName, e.id, st.grade.grade) FROM Evaluation e "
            + "JOIN e.students st JOIN st.student s WHERE e.course.id = :courseId AND "
            + "e.subject.id = :subjectId AND e.year.year = 2021 AND e.type.id = 2 ORDER BY s.id")
    Optional<List<StudentWithEvaluationDTO>> fetchWorkshopsOfStudentsOfASubjectInACourse(
            @Param("courseId") Integer courseId, @Param("subjectId") Integer subjectId);

    @Query("SELECT new com.ufro.culmingapp.student.application.DTOs.StudentWithHomeworkDTO(s.id, "
            + "s.fullName.firstName, s.fullName.lastName, h.id, h.instruction.instruction, h.deadline, " +
            "sh.state.name) FROM Homework h JOIN h.students sh JOIN sh.student s WHERE h.course.id = :courseId "
            + "AND h.subject.id = :subjectId AND h.year.year = 2021 AND h.deadline.deadline BETWEEN "
            + " :start AND :end ORDER BY s.id")
    Optional<List<StudentWithHomeworkDTO>> fetchHomeworksOfStudentsOfASubjectInACourse(
            @Param("courseId") Integer courseId, @Param("subjectId") Integer subjectId,
            @Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT new com.ufro.culmingapp.student.application.DTOs.StudentWithHomeworkDTO(s.id, "
            + "s.fullName.firstName, s.fullName.lastName, h.id, h.instruction.instruction, h.deadline, " +
            "sh.state.name) FROM Homework h JOIN h.students sh JOIN sh.student s WHERE h.course.id = :courseId "
            + "AND h.subject.id = :subjectId AND s.id = :studentId AND h.year.year = 2021 AND h.deadline.deadline " +
            "BETWEEN :start AND :end")
    Optional<List<StudentWithHomeworkDTO>> fetchHomeworksOfAStudentOfASubjectInACourse(
            @Param("courseId") Integer courseId, @Param("subjectId") Integer subjectId,
            @Param("studentId") Long studentId, @Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT new com.ufro.culmingapp.student.application.DTOs.StudentWithAssistanceDTO(s.id, "
            + "s.fullName.firstName, s.fullName.lastName, a.id, sa.isPresent) FROM Assistance a JOIN a.students sa "
            + "JOIN sa.student s WHERE a.course.id = :courseId AND a.subject.id = :subjectId AND a.year.year = 2021 "
            + "AND a.date.date BETWEEN :start AND :end ORDER BY s.id")
    Optional<List<StudentWithAssistanceDTO>> fetchAssistancesOfStudentsOfASubjectInACourse(
            @Param("courseId") Integer courseId, @Param("subjectId") Integer subjectId,
            @Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT new com.ufro.culmingapp.student.application.DTOs.StudentWithAssistanceDTO(s.id, "
            + "s.fullName.firstName, s.fullName.lastName, a.id, sa.isPresent) FROM Assistance a JOIN a.students sa "
            + "JOIN sa.student s WHERE a.course.id = :courseId AND a.subject.id = :subjectId AND s.id = :studentId " +
            "AND a.year.year = 2021 AND a.date.date BETWEEN :start AND :end")
    Optional<List<StudentWithAssistanceDTO>> fetchAssistancesOfStudentOfASubjectInACourse(
            @Param("courseId") Integer courseId, @Param("subjectId") Integer subjectId,
            @Param("studentId") Long studentId, @Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("SELECT s FROM Student s JOIN s.courses sc JOIN s.subjects ss WHERE sc.course.id = :courseId AND "
            + "sc.year = 2021 AND ss.subject.id = :subjectId AND ss.year = 2021")
    Optional<List<Student>> fetchStudentsTakingASubjectInACourse(@Param("courseId") Integer courseId, @Param(
            "subjectId") Integer subjectId);
}
