package com.ufro.culmingapp.subject.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query(value = "SELECT * FROM SUBJECTS WHERE ID IN" +
           "(SELECT SUBJECT_ID FROM TEACHERS_SUBJECTS WHERE TEACHER_ID = :teacherId AND SUBJECT_ID IN " +
           "(SELECT SUBJECT_ID FROM STUDENTS_SUBJECTS WHERE STUDENT_ID IN" +
           "(SELECT ID FROM STUDENTS WHERE ID IN " +
           "(SELECT STUDENT_ID FROM STUDENTS_COURSES WHERE COURSE_ID= :courseId))))", nativeQuery = true)
    public Optional<List<Subject>> findSubjectsTaughtByATeacherInACourse(@Param("teacherId") Long teacherId,
            @Param("courseId") Long courseId);

}
