package com.ufro.culmingapp.teacher.domain;

import com.ufro.culmingapp.course.application.DTOs.CourseWithSubjectDTO;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherHomeDTO;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherMiniProfileDTO;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherWithSubjectDTO;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherWithoutEmailAndActivityDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

    @Query("SELECT new com.ufro.culmingapp.teacher.application.DTOs.TeacherWithoutEmailAndActivityDTO(t.id, " +
            "t.fullName, t.address, t.dateOfBirth, t.phone, t.enrollmentDate) FROM Teacher t WHERE t.id = :teacherId")
    Optional<TeacherWithoutEmailAndActivityDTO> fetchTeacherWithoutEmailAndActivity(@Param("teacherId") Long teacherId);

    @Query("SELECT new com.ufro.culmingapp.teacher.application.DTOs.TeacherMiniProfileDTO(" +
            "t.id, t.fullName, t.address, t.phone, t.email) FROM Teacher t WHERE t.school.id = " +
            ":schoolId AND t.isActive = true")
    Optional<List<TeacherMiniProfileDTO>> fetchTeachersWithMiniProfile(@Param("schoolId") Long schoolId);

    @Query("SELECT new com.ufro.culmingapp.course.application.DTOs.CourseWithSubjectDTO("
            + "c.id, c.level, s.id as subjectId, s.name.name as subjectName) "
            + "FROM Course c JOIN c.subjects cs JOIN cs.subject s "
            + "WHERE cs.teacher.id = :teacherId AND cs.year = 2021")
    Optional<List<CourseWithSubjectDTO>> fetchCoursesWithSubjectsWhereATeacherTeach(@Param("teacherId") Long teacherId);

    @Query("SELECT t FROM Teacher t WHERE t.email.email = :email")
    Optional<Teacher> fetchByEmail(@Param("email") String email);

}
