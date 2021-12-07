package com.ufro.culmingapp.tutor.infrastructure;

import com.ufro.culmingapp.shared.domain.exceptions.AgeNotAccepted;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.domain.exceptions.WrongEmailFormat;
import com.ufro.culmingapp.shared.domain.valueobjects.Address;
import com.ufro.culmingapp.shared.domain.valueobjects.Email;
import com.ufro.culmingapp.shared.domain.valueobjects.FullName;
import com.ufro.culmingapp.tutor.application.DTOs.TutorDTO;
import com.ufro.culmingapp.tutor.application.TutorCreator;
import com.ufro.culmingapp.tutor.domain.TutorAge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class TutorPostController {

    @Autowired
    private TutorCreator creator;

    @PostMapping("/tutors")
    public ResponseEntity<?> addNewTutor(@RequestBody TutorDTO newTutor) {
        try {
            FullName fullName = new FullName(newTutor.getFirstName(), newTutor.getMiddleName(),
                    newTutor.getLastName(), newTutor.getSecondSurname());
            Email email = new Email(newTutor.getEmail());
            Address address = new Address(newTutor.getAddress());
            TutorAge age = new TutorAge(newTutor.getAge());

            TutorDTO tutor = creator.create(fullName, age, email, address);

            return ResponseEntity.status(HttpStatus.CREATED).body(tutor);

        } catch (NullFieldNotPermitted | WrongEmailFormat | AgeNotAccepted e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }
}
