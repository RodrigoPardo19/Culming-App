package com.ufro.culmingapp.tutor.domain;

import com.ufro.culmingapp.student.application.DTOs.StudentWithFullNameDTO;
import com.ufro.culmingapp.tutor.application.TutorWithFullNameDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    @Query("SELECT new com.ufro.culmingapp.student.application.DTOs.StudentWithFullNameDTO(s.id, s.fullName) FROM " +
            "Tutor t JOIN t.students s WHERE t.id = :id")
    Optional<List<StudentWithFullNameDTO>> fetchTutorPupils(@Param("id") Long id);

    @Query("SELECT new com.ufro.culmingapp.tutor.application.TutorWithFullNameDTO(t.id, t.fullName) FROM Tutor t " +
            "WHERE t.id = :id")
    Optional<TutorWithFullNameDTO> fetchTutorWithFullName(@Param("id") Long id);

}
