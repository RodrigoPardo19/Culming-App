package com.ufro.culmingapp.tutor.domain;

import com.ufro.culmingapp.student.application.DTOs.StudentWithCourseAndFullnameDTO;
import com.ufro.culmingapp.tutor.application.TutorWithFullNameDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    @Query("SELECT DISTINCT new com.ufro.culmingapp.student.application.DTOs.StudentWithCourseAndFullnameDTO(s.id, s.fullName," +
            " c.course.id AS courseId) FROM Tutor t JOIN t.students s JOIN s.courses c WHERE t.id = :id AND c.year = 2021")
    Optional<List<StudentWithCourseAndFullnameDTO>> fetchTutorPupils(@Param("id") Long id);

    @Query("SELECT new com.ufro.culmingapp.tutor.application.TutorWithFullNameDTO(t.id, t.fullName) FROM Tutor t " +
            "WHERE t.id = :id")
    Optional<TutorWithFullNameDTO> fetchTutorWithFullName(@Param("id") Long id);

    @Query("SELECT DISTINCT new com.ufro.culmingapp.tutor.application.TutorWithFullNameDTO(t.id, t.fullName) FROM " +
            "Tutor t ")
    Optional<List<TutorWithFullNameDTO>> fetchSchoolTutors(@Param("schoolId") Long schoolId);

    @Query("SELECT t FROM Tutor t WHERE t.email.email = :email")
    Optional<Tutor> fetchByEmail(@Param("email") String email);

}
