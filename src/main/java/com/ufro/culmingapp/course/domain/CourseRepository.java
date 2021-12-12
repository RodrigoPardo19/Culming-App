package com.ufro.culmingapp.course.domain;

import java.util.List;
import java.util.Optional;

import com.ufro.culmingapp.course.application.DTOs.CourseDTO;
import com.ufro.culmingapp.course.application.DTOs.CourseWithSubjectDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    /**
      * Fetch courses where the teacher taught or actually teach
      */
    @Query(value = "SELECT * FROM COURSES WHERE YEAR_OF_GENERATION= :year AND ID IN " +
           "(SELECT COURSE_ID FROM STUDENTS_COURSES WHERE STUDENT_ID IN " +
           "(SELECT ID FROM STUDENTS WHERE ID IN " +
           "(SELECT STUDENT_ID FROM STUDENTS_SUBJECTS WHERE SUBJECT_ID IN " +
           "(SELECT ID FROM SUBJECTS WHERE ID IN " +
           "(SELECT SUBJECT_ID FROM TEACHERS_SUBJECTS WHERE TEACHER_ID= :id)))))", nativeQuery = true)
    public Optional<List<Course>> fetchCoursesWhereTeacherTaughtDuringTheYear(@Param("id") Long id,
            @Param("year") Integer year);

    /**
      * Fetch courses where the teacher taught or actually teach and with the subjects that taught during these year
      */
    @Query(value = "SELECT ID, NAME, GROUP_CONCAT (SUBJECT_ID) AS SUBJECTIDS, " +
           "GROUP_CONCAT (SUBJECT_NAME) AS SUBJECTS " +
           "FROM (SELECT COURSES.ID, COURSES.NAME, SUBJECTS.ID as SUBJECT_ID, SUBJECTS.NAME AS SUBJECT_NAME FROM " +
           "COURSES INNER JOIN STUDENTS_COURSES INNER JOIN STUDENTS INNER JOIN STUDENTS_SUBJECTS INNER JOIN SUBJECTS  INNER JOIN TEACHERS_SUBJECTS " +
           "ON COURSES.ID = STUDENTS_COURSES.COURSE_ID AND STUDENTS_COURSES.STUDENT_ID = STUDENTS.ID AND STUDENTS.ID = STUDENTS_SUBJECTS.STUDENT_ID " +
           "AND STUDENTS_SUBJECTS.SUBJECT_ID = SUBJECTS.ID AND SUBJECTS.ID = TEACHERS_SUBJECTS.SUBJECT_ID AND TEACHERS_SUBJECTS.TEACHER_ID= :id " +
           "AND COURSES.YEAR_OF_GENERATION= :year) GROUP BY ID", nativeQuery = true)
    public Optional<List<CourseWithSubjectDTO>> fetchCoursesWithSubjectsTaughtByATeacher(
            @Param("id") Long id, @Param("year") Integer year);

    @Query("SELECT new com.ufro.culmingapp.course.application.DTOs.CourseDTO(c.id, c.level) FROM Course c JOIN c.schools s WHERE s.id = :schoolId")
    public Optional<List<CourseDTO>> fetchAllSchoolCourses(@Param("schoolId") Long schoolId);


}
