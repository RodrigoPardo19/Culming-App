package com.ufro.culmingapp.subject.domain;

import com.ufro.culmingapp.subject.application.DTOs.SubjectDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query(value = "SELECT * FROM SUBJECTS WHERE ID IN" +
            "(SELECT SUBJECT_ID FROM TEACHERS_SUBJECTS WHERE TEACHER_ID = :teacherId AND SUBJECT_ID IN " +
            "(SELECT SUBJECT_ID FROM STUDENTS_SUBJECTS WHERE STUDENT_ID IN" +
            "(SELECT ID FROM STUDENTS WHERE ID IN " +
            "(SELECT STUDENT_ID FROM STUDENTS_COURSES WHERE COURSE_ID= :courseId))))", nativeQuery = true)
    Optional<List<Subject>> findSubjectsTaughtByATeacherInACourse(@Param("teacherId") Long teacherId,
                                                                  @Param("courseId") Long courseId);

    @Query("SELECT new com.ufro.culmingapp.subject.application.DTOs.SubjectDTO(s.id, s.name.name) FROM Subject s JOIN " +
            " s.students sts WHERE sts.year = 2021 AND sts.student.id = :studentId")
    Optional<List<SubjectDTO>> fetchSubjectsTakenByAStudent(@Param("studentId") Long studentId);
}
