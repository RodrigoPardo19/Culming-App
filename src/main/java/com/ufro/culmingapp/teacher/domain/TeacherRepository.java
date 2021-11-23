package com.ufro.culmingapp.teacher.domain;

import java.util.List;
import java.util.Optional;

import com.ufro.culmingapp.course.application.DTOs.CourseWithSubjectDTO;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherHomeDTO;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherWithSubjectDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT new com.ufro.culmingapp.teacher.application.DTOs.TeacherHomeDTO(" +
            "t.id, t.fullName.firstName, t.fullName.middleName, t.fullName.lastName, " +
            "t.fullName.secondSurname) FROM Teacher t WHERE t.id = :teacherId")
    Optional<TeacherHomeDTO> fetchTeacherHomeById(@Param("teacherId") Long teacherId);

    @Query("SELECT DISTINCT new com.ufro.culmingapp.teacher.application.DTOs.TeacherWithSubjectDTO(" +
            "t.id, t.fullName.firstName, t.fullName.middleName, t.fullName.lastName, " +
            "t.fullName.secondSurname, t.dateOfBirth, t.address.address, t.phone.phone, " +
            "t.email.email, t.biography, s.id AS subjectId, s.name.name AS subjectName) " +
            "FROM Teacher t JOIN t.subjects st JOIN st.subject s " +
            "WHERE t.id = :teacherId AND st.year = 2021")
    Optional<List<TeacherWithSubjectDTO>> fetchTeacherProfileById(@Param("teacherId") Long teacherId);

    @Query("SELECT new com.ufro.culmingapp.course.application.DTOs.CourseWithSubjectDTO("
            + "c.id, c.level, s.id as subjectId, s.name.name as subjectName) "
            + "FROM Course c JOIN c.subjects cs JOIN cs.subject s "
            + "WHERE cs.teacher.id = :teacherId AND cs.year = 2021")
    Optional<List<CourseWithSubjectDTO>> fetchCoursesWithSubjectsWhereATeacherTeach(@Param("teacherId") Long teacherId);

    Optional<Teacher> findByEmail(String email);

}
