package com.ufro.culmingapp.student.application;

import com.ufro.culmingapp.Role.application.RoleFinder;
import com.ufro.culmingapp.Role.domain.Role;
import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.school.application.SchoolFinder;
import com.ufro.culmingapp.school.domain.School;
import com.ufro.culmingapp.school.domain.exceptions.SchoolNotFound;
import com.ufro.culmingapp.shared.domain.valueobjects.*;
import com.ufro.culmingapp.student.application.DTOs.StudentDTO;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.student.domain.StudentRepository;
import com.ufro.culmingapp.student.domain.exceptions.StudentNotFound;
import com.ufro.culmingapp.tutor.application.TutorFinder;
import com.ufro.culmingapp.tutor.domain.Tutor;
import com.ufro.culmingapp.tutor.domain.TutorNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCreator {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private SchoolFinder schoolFinder;

    @Autowired
    private StudentMapper mapper;

    @Autowired
    private TutorFinder tutorFinder;

    @Autowired
    private StudentCourseRegister studentCourseRegister;

    @Autowired
    private StudentSubjectRegister studentSubjectRegister;

    @Autowired
    private RoleFinder roleFinder;

    public StudentDTO create(FullName fullName, Email email, Address address, DateOfBirth dateOfBirth,
            EnrollmentDate enrollmentDate, Boolean isActive, Long schoolId, Integer courseId,
            Long tutorId, GenerationYear year)
            throws SchoolNotFound, CourseNotFound, TutorNotFound, StudentNotFound {
        final String STUDENT_ROLE = "ROLE_STUDENT";
        Role role = roleFinder.getRole(STUDENT_ROLE);
        School school = schoolFinder.findById(schoolId);
        Tutor tutor = tutorFinder.findById(tutorId);
        Student student = new Student(fullName, dateOfBirth, email, address, enrollmentDate, isActive);
        student.setSchool(school);
        student.setTutor(tutor);
        student.setRole(role);
        repository.save(student);
        Long studentId = student.getId();
        studentCourseRegister.register(studentId, courseId, year);
        studentSubjectRegister.registerRequiredSubjects(studentId, year);
        return mapper.mapToStudentDTO(student, courseId, tutorId, year.getYear());
    }

}
