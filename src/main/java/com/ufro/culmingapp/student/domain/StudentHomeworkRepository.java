package com.ufro.culmingapp.student.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentHomeworkRepository extends JpaRepository<StudentHomework, Long> {

    Optional<StudentHomework> findByStudentIdAndHomeworkId(Long studentId, Long homeworkId);
}
