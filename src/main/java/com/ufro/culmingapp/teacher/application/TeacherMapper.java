package com.ufro.culmingapp.teacher.application;

import com.ufro.culmingapp.course.application.DTOs.CourseWithSubjectDTO;
import com.ufro.culmingapp.course.application.DTOs.CourseWithSubjectsDTO;
import com.ufro.culmingapp.subject.application.DTOs.SubjectDTO;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherDTO;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherHomeDTO;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherProfileDTO;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherWithSubjectDTO;
import com.ufro.culmingapp.teacher.domain.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TeacherMapper {

    public TeacherMapper() {
        // Used only for spring
    }

    public TeacherHomeDTO mapTeacherToTeacherHome(Teacher teacher) {
        TeacherHomeDTO home = new TeacherHomeDTO(teacher.getId(),
                teacher.getFullName().getFirstName(),
                teacher.getFullName().getMiddleName(), teacher.getFullName().getLastName(),
                teacher.getFullName().getSecondSurname());
        return home;
    }

    public TeacherProfileDTO transformTeacherWithSubjectInTeacherProfile(
            List<TeacherWithSubjectDTO> teacherWithSubjects) {

        TeacherWithSubjectDTO teacher = teacherWithSubjects.get(0);

        Long id = teacher.getId();
        String firstName = teacher.getFirstName();
        String middleName = teacher.getMiddleName();
        String lastName = teacher.getLastName();
        String secondSurname = teacher.getSecondSurname();
        String dateOfBirth = teacher.getDateOfBirth();
        String address = teacher.getAddress();
        String phone = teacher.getPhone();
        String email = teacher.getEmail();
        String biography = teacher.getBiography();

        Set<SubjectDTO> subjects = new HashSet<>();

        teacherWithSubjects.stream()
                .forEach(s -> subjects.add(new SubjectDTO(s.getSubjectId(), s.getSubjectName())));

        return new TeacherProfileDTO(id, firstName, middleName,
                lastName, secondSurname, dateOfBirth, address, phone, email, biography, subjects);
    }

    public List<CourseWithSubjectsDTO>
    transformInNestedObject(List<CourseWithSubjectDTO> coursesWithSubjects) {

        List<CourseWithSubjectsDTO> coursesWithNestedSubjects = new ArrayList<>();

        Integer id;
        Set<SubjectDTO> subjects = new HashSet<>();

        Integer pivotId = coursesWithSubjects.get(0).getId();

        for (int i = 0; i < coursesWithSubjects.size(); i++) {

            id = coursesWithSubjects.get(i).getId();

            if (pivotId != id) {
                coursesWithNestedSubjects
                        .add(new CourseWithSubjectsDTO(coursesWithSubjects.get(i - 1).getId(),
                                coursesWithSubjects.get(i - 1).getLevel(),
                                subjects));
                subjects.clear();
            }

            subjects.add(new SubjectDTO(coursesWithSubjects.get(i).getSubjectId(),
                    coursesWithSubjects.get(i).getSubjectName()));
            pivotId = id;
        }

        coursesWithNestedSubjects.add(new CourseWithSubjectsDTO(
                coursesWithSubjects.get(coursesWithSubjects.size() - 1).getId(),
                coursesWithSubjects.get(coursesWithSubjects.size() - 1).getLevel(),
                subjects));

        return coursesWithNestedSubjects;
    }

    public TeacherDTO mapToTeacherDTO(Teacher teacher) {
        Long id = teacher.getId();
        String firstName = teacher.getFullName().getFirstName();
        String middleName = teacher.getFullName().getMiddleName();
        String lastName = teacher.getFullName().getLastName();
        String secondSurname = teacher.getFullName().getSecondSurname();
        String email = teacher.getEmail().getEmail();
        String address = teacher.getAddress().getAddress();
        String dateOfBirth = teacher.getDateOfBirth().getStringDateOfBirth();
        String phone = teacher.getPhone().getPhone();
        String enrollmentDate = teacher.getEnrollmentDate().getStringEnrollmentDate();
        Boolean isActive = teacher.isActive();
        return new TeacherDTO(id, firstName, middleName, lastName, secondSurname, email, address, dateOfBirth, phone,
                enrollmentDate, isActive);
    }

}
