package com.ufro.culmingapp.teacher.application;

import java.util.List;
import java.util.Optional;

import com.ufro.culmingapp.course.application.DTOs.CourseWithSubjectDTO;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.course.domain.CourseRepository;
import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.subject.domain.SubjectRepository;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.teacher.domain.TeacherRepository;
import com.ufro.culmingapp.teacher.domain.exceptions.TeacherNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherFinderService {

    private TeacherRepository repository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    public TeacherFinderService(TeacherRepository repository) {
        this.repository = repository;
    }

    public Teacher findById(Long id) throws TeacherNotFound {
        Optional<Teacher> teacher = repository.findById(id);
        if (!teacher.isPresent()) {
            throw new TeacherNotFound(id);
        }
        return teacher.get();
    }

    public Teacher findByEmail(String email) throws TeacherNotFound {
        Optional<Teacher> teacher = repository.findByEmail(email);
        if (teacher.isPresent()) {
            return teacher.get();
        } else {
            throw new TeacherNotFound(email);
        }
    }

    public List<Subject> getSubjects(Long id) throws TeacherNotFound {
        Optional<Teacher> teacher = repository.findById(id);
        if (teacher.isPresent()) {
            return teacher.get().getSubjects();
        } else {
            throw new TeacherNotFound(id);
        }
    }

    public List<Course> findCoursesWhereTeacherTaughtDuringTheYear(Long id, Integer year)
        throws TeacherNotFound {
        Teacher teacher = this.findById(id);
        Optional<List<Course>> courses = courseRepository
                .fetchCoursesWhereTeacherTaughtDuringTheYear(teacher.getId(),
                        year);
        return courses.get();
    }

    public List<Subject> findSubjectsTeaughtByATeacherInACourse(Long teacherId, Long courseId)
        throws TeacherNotFound {
        Teacher teacher = this.findById(teacherId);
        Optional<List<Subject>> subjects = subjectRepository
                .findSubjectsTaughtByATeacherInACourse(teacher.getId(), courseId);
        return subjects.get();
    }

    public List<CourseWithSubjectDTO> findCoursesWithSubjectsTaughtByATeacher(Long id, Integer year)
        throws TeacherNotFound {
        Teacher teacher = this.findById(id);
        Optional<List<CourseWithSubjectDTO>> coursesWithSubjects = courseRepository
                .fetchCoursesWithSubjectsTaughtByATeacher(teacher.getId(), year);
        return coursesWithSubjects.get();
    }
}
