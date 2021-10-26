package com.ufro.culmingapp.teacher.application;

import com.ufro.culmingapp.shared.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.exceptions.WrongEmailFormat;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.teacher.domain.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherUpdaterService {

    @Autowired
    private TeacherRepository repository;

    public void updateTeacherValues(Teacher teacher, Teacher teacherUpdated) {
        teacher.setMiddleName(teacherUpdated.getMiddleName());
        teacher.setSecondSurname(teacherUpdated.getSecondSurname());
        teacher.setBiography(teacherUpdated.getBiography());
        teacher.setPhoto(teacherUpdated.getPhoto());
        this.repository.save(teacher);
    }

    public Teacher changeAddress(Teacher teacher, String address) throws NullFieldNotPermitted {
        if (address != null && !address.isBlank()) {
            teacher.setAddress(address);
            repository.save(teacher);
            return teacher;
        } else {
            throw new NullFieldNotPermitted("address");
        }
    }

    public Teacher changePhone(Teacher teacher, String phone) throws NullFieldNotPermitted {
        if (phone != null && phone != " ") {
            teacher.setPhone(phone);
            repository.save(teacher);
            return teacher;
        } else {
            throw new NullFieldNotPermitted("phone");
        }
    }

    public Teacher changeEmail(Teacher teacher, String email) throws NullFieldNotPermitted, WrongEmailFormat {
        if (email == null) {
            throw new NullFieldNotPermitted("email");
        } else if (!isValid(email)) {
            throw new WrongEmailFormat(email);
        } else {
            teacher.setEmail(email);
            repository.save(teacher);
            return teacher;
        }
    }

    public Boolean isValid(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        return email.matches(regex);
    }

    public void changePassword(String password) {
        // Waiting for implementation
    }

}
