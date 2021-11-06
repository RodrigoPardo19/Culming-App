package com.ufro.culmingapp.teacher.domain;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    public Optional<Teacher> findByEmail(String email);

}
