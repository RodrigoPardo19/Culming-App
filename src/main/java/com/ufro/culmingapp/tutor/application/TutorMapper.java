package com.ufro.culmingapp.tutor.application;

import com.ufro.culmingapp.tutor.application.DTOs.TutorDTO;
import com.ufro.culmingapp.tutor.domain.Tutor;
import org.springframework.stereotype.Service;

@Service
public class TutorMapper {

    public TutorDTO mapToTutorDTO(Tutor tutor) {
        Long id = tutor.getId();
        String firstName = tutor.getFullName().getFirstName();
        String middleName = tutor.getFullName().getMiddleName();
        String lastName = tutor.getFullName().getLastName();
        String secondSurname = tutor.getFullName().getSecondSurname();
        Integer age = tutor.getAge().getAge();
        String email = tutor.getEmail().getEmail();
        String address = tutor.getAddress().getAddress();
        return new TutorDTO(id, firstName, middleName, lastName, secondSurname, age, email, address);
    }
}
