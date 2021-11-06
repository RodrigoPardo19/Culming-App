package com.ufro.culmingapp.course.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "SELECT * FROM COURSES WHERE YEAR_OF_GENERATION= :year AND ID IN (SELECT COURSE_ID FROM STUDENTS_COURSES WHERE STUDENT_ID IN (SELECT ID FROM STUDENTS WHERE ID IN (SELECT STUDENT_ID FROM STUDENTS_SUBJECTS WHERE SUBJECT_ID IN (SELECT ID FROM SUBJECTS WHERE ID IN (SELECT SUBJECT_ID FROM TEACHERS_SUBJECTS WHERE TEACHER_ID= :id)))))", nativeQuery = true)
    public Optional<List<Course>> fetchCoursesWhereTeacherTaughtDuringTheYear(@Param("id") Long id,
            @Param("year") Integer year);
}
