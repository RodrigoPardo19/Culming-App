package com.ufro.culmingapp.teacher.application;

import com.ufro.culmingapp.shared.application.EmailManagerService;
import com.ufro.culmingapp.shared.domain.valueobjects.Address;
import com.ufro.culmingapp.shared.domain.valueobjects.DateOfBirth;
import com.ufro.culmingapp.shared.domain.valueobjects.Email;
import com.ufro.culmingapp.shared.domain.valueobjects.FullName;
import com.ufro.culmingapp.shared.domain.valueobjects.Phone;
import com.ufro.culmingapp.teacher.domain.Teacher;
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

    @Autowired
    private EmailManagerService emailSender;

    public Teacher updateProfile(Long id, FullName name, Address address, Phone phone, DateOfBirth dateOfBirth,
            String biography) throws TeacherNotFound {
        Teacher teacher = finder.findById(id);
        teacher.setFullName(name);
        teacher.setAddress(address);
        teacher.setPhone(phone);
        teacher.setDateOfBirth(dateOfBirth);
        teacher.setBiography(biography);
        repository.save(teacher);
        return teacher;
    }

    public void changeEmail(Long id, Email email) throws TeacherNotFound {
        // Implement with JWT
    }

    public void changePassword(String password) {
        // Implement with JWT
    }

}
