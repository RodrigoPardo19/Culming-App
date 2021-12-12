package com.ufro.culmingapp.teacher.application;

import com.ufro.culmingapp.course.application.DTOs.CourseWithSubjectDTO;
import com.ufro.culmingapp.course.application.DTOs.CourseWithSubjectsDTO;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.course.domain.CourseRepository;
import com.ufro.culmingapp.coursesubjectteacher.domain.CourseSubjectTeacher;
import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.subject.domain.SubjectRepository;
import com.ufro.culmingapp.teacher.application.DTOs.*;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.teacher.domain.TeacherRepository;
import com.ufro.culmingapp.teacher.domain.exceptions.TeacherNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeacherFinderService {

    private TeacherRepository repository;

    @Autowired
    private TeacherMapper mapper;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    public TeacherFinderService(TeacherRepository repository) {
        this.repository = repository;
    }

    public TeacherWithoutEmailAndActivityDTO findTeacher(Long id) throws TeacherNotFound {
        Optional<TeacherWithoutEmailAndActivityDTO> teacher = repository.fetchTeacherWithoutEmailAndActivity(id);
        if (teacher.isEmpty()) {
            throw new TeacherNotFound(id);
        }
        return teacher.get();
    }

    public Teacher findById(Long id) throws TeacherNotFound {
        Optional<Teacher> teacher = repository.findById(id);
        if (!teacher.isPresent()) {
            throw new TeacherNotFound(id);
        }
        return teacher.get();
    }

    public Teacher findByEmail(String email) throws TeacherNotFound {
        Optional<Teacher> teacher = repository.fetchByEmail(email);
        if (teacher.isPresent()) {
            return teacher.get();
        } else {
            throw new TeacherNotFound(email);
        }
    }

    public Optional<Teacher> getByEmail(String email) {
        return repository.fetchByEmail(email);
    }

    public TeacherHomeDTO findTeacherHome(Long id) throws TeacherNotFound {
        Optional<TeacherHomeDTO> teacherHome = repository.fetchTeacherHomeById(id);
        if (!teacherHome.isPresent()) {
            throw new TeacherNotFound(id);
        }
        return teacherHome.get();
    }

    public TeacherProfileDTO findTeacherProfile(Long id) throws TeacherNotFound {
        Optional<List<TeacherWithSubjectDTO>> teacherWithSubjects = repository
                .fetchTeacherProfileById(id);
        if (!teacherWithSubjects.isPresent()) {
            throw new TeacherNotFound(id);
        }
        return mapper.transformTeacherWithSubjectInTeacherProfile(teacherWithSubjects.get());
    }

    public List<TeacherMiniProfileDTO> findTeacherMiniProfile(Long id) throws TeacherNotFound {
        Optional<List<TeacherMiniProfileDTO>> teachers = repository
                .fetchTeachersWithMiniProfile(id);
        if (!teachers.isPresent()) {
            throw new TeacherNotFound(id);
        }
        return teachers.get();
    }

    public Set<CourseSubjectTeacher> getSubjects(Long id) throws TeacherNotFound {
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

    public List<CourseWithSubjectsDTO> findCoursesWithSubjectsTaughtByATeacher(Long id, Integer year)
            throws TeacherNotFound {
        Teacher teacher = this.findById(id);
        Optional<List<CourseWithSubjectDTO>> coursesWithSubjects = repository
                .fetchCoursesWithSubjectsWhereATeacherTeach(id);
        return mapper.transformInNestedObject(coursesWithSubjects.get());
    }
}
