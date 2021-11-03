package com.ufro.culmingapp.teacher.application;

import com.ufro.culmingapp.teacher.application.DTOs.TeacherProfileDTO;
import com.ufro.culmingapp.teacher.domain.Teacher;

import org.springframework.stereotype.Service;

@Service
public class TeacherMapper {

    public TeacherMapper() {
        //
    }

    public TeacherProfileDTO mapTeacherToTeacherProfile(Teacher teacher) {
        TeacherProfileDTO profile = new TeacherProfileDTO(teacher.getId(), teacher.getFullName().getFirstName(),
                teacher.getFullName().getMiddleName(), teacher.getFullName().getLastName(),
                teacher.getFullName().getSecondSurname(), teacher.getDateOfBirth().getStringDateOfBirth(),
                teacher.getAddress().getAddress(), teacher.getPhone().getPhone(), teacher.getEmail(),
                teacher.getBiography(), teacher.getSubjects());
        return profile;
    }

}
