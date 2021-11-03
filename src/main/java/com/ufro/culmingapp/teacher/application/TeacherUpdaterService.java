package com.ufro.culmingapp.teacher.application;

import com.ufro.culmingapp.shared.domain.valueobjects.Address;
import com.ufro.culmingapp.shared.domain.valueobjects.DateOfBirth;
import com.ufro.culmingapp.shared.domain.valueobjects.Phone;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.teacher.domain.TeacherName;
import com.ufro.culmingapp.teacher.domain.TeacherRepository;
import com.ufro.culmingapp.teacher.domain.exceptions.TeacherNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherUpdaterService {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private TeacherFinderService finder;

    public Teacher updateProfile(Long id, TeacherName name, Address address, Phone phone, DateOfBirth dateOfBirth,
            String biography) throws TeacherNotFound {
        Teacher teacher = finder.findById(id);
        teacher.updateName(name);
        teacher.updateAddress(address);
        teacher.updatePhone(phone);
        teacher.updateDateOfBirth(dateOfBirth);
        teacher.updateBiography(biography);
        repository.save(teacher);
        return teacher;
    }

    public void changeEmail(Teacher teacher, String email) {
        //
    }

    public void changePassword(String password) {
        // Waiting for implementation
    }

}
