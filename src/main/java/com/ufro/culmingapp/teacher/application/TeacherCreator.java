package com.ufro.culmingapp.teacher.application;

import com.ufro.culmingapp.school.application.SchoolFinder;
import com.ufro.culmingapp.school.domain.School;
import com.ufro.culmingapp.school.domain.exceptions.SchoolNotFound;
import com.ufro.culmingapp.shared.domain.valueobjects.*;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherDTO;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.teacher.domain.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherCreator {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private SchoolFinder schoolFinder;

    @Autowired
    private TeacherMapper mapper;

    public TeacherDTO create(FullName fullName, Email email, Address address, DateOfBirth dateOfBirth, Phone phone,
            EnrollmentDate enrollmentDate, Boolean isActive, Long schoolId) throws SchoolNotFound {

        School school = schoolFinder.findById(schoolId);

        Teacher teacher = new Teacher(fullName, email, address, dateOfBirth, phone, enrollmentDate, isActive);
        teacher.setSchool(school);
        repository.save(teacher);

        return mapper.mapToTeacherDTO(teacher);
    }

}
