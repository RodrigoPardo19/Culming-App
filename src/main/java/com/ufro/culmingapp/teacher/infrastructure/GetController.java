package com.ufro.culmingapp.teacher.infrastructure;

import java.util.List;

import com.ufro.culmingapp.course.application.DTOs.CourseWithSubjectDTO;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.teacher.application.TeacherFinderService;
import com.ufro.culmingapp.teacher.application.TeacherMapper;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherHomeDTO;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherProfileDTO;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.teacher.domain.exceptions.TeacherNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class GetController {

    @Autowired
    private TeacherFinderService finder;

    @Autowired
    private TeacherMapper mapper;

    @GetMapping("/teachers/{id}/home")
    public ResponseEntity<?> getTeacherHome(@PathVariable Long id) {
        try {
            Teacher teacher = finder.findById(id);
            TeacherHomeDTO home = mapper.mapTeacherToTeacherHome(teacher);
            return ResponseEntity.status(HttpStatus.OK).body(home);
        } catch (TeacherNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/teachers/{id}/profile")
    public ResponseEntity<?> getTeacherProfile(@PathVariable Long id) {
        try {
            Teacher teacher = finder.findById(id);
            TeacherProfileDTO profile = mapper.mapTeacherToTeacherProfile(teacher);
            return ResponseEntity.status(HttpStatus.OK).body(profile);
        } catch (TeacherNotFound e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/teachers/{id}/courses/{year}")
    public ResponseEntity<?> getTeacherCourses(@PathVariable Long id, @PathVariable Integer year) {
        try {
            List<Course> courses = finder.findCoursesWhereTeacherTaughtDuringTheYear(id, year);
            return ResponseEntity.status(HttpStatus.OK).body(courses);
        } catch (TeacherNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/teachers/{teacherId}/courses/{courseId}/subjects")
    public ResponseEntity<?> getSubjectsTaughtByATeacherInACourse(@PathVariable Long teacherId,
            @PathVariable Long courseId) {
        try {
            List<Subject> subjects = finder.findSubjectsTeaughtByATeacherInACourse(teacherId,
                    courseId);
            return ResponseEntity.status(HttpStatus.OK).body(subjects);
        } catch (TeacherNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/teachers/{id}/courses-subjects/{year}")
    public ResponseEntity<?> getCoursesWithSubjects(@PathVariable Long id,
            @PathVariable Integer year) {
        try {
            List<CourseWithSubjectDTO> coursesWithSubjects = finder
                    .findCoursesWithSubjectsTaughtByATeacher(id, year);
            return ResponseEntity.status(HttpStatus.OK).body(coursesWithSubjects);
        } catch (TeacherNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
        }

    }

    @GetMapping("/teachers/{id}/subjects")
    public ResponseEntity<?> getTeacherSubjects(@PathVariable Long id) {
        try {
            List<Subject> subjects = finder.getSubjects(id);
            return ResponseEntity.status(HttpStatus.OK).body(subjects);
        } catch (TeacherNotFound e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
