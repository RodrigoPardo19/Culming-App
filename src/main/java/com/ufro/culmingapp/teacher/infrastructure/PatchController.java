package com.ufro.culmingapp.teacher.infrastructure;

import com.ufro.culmingapp.shared.domain.valueobjects.Address;
import com.ufro.culmingapp.shared.domain.valueobjects.DateOfBirth;
import com.ufro.culmingapp.shared.domain.valueobjects.FullName;
import com.ufro.culmingapp.shared.domain.valueobjects.Phone;
import com.ufro.culmingapp.teacher.application.TeacherFinderService;
import com.ufro.culmingapp.teacher.application.TeacherMapper;
import com.ufro.culmingapp.teacher.application.TeacherUpdaterService;
import com.ufro.culmingapp.teacher.application.DTOs.TeacherProfileDTO;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.teacher.domain.exceptions.TeacherNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

import com.ufro.culmingapp.shared.application.EmailManagerService;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.domain.exceptions.WrongLength;

@RestController
public class PatchController {

    @Autowired
    private TeacherUpdaterService updater;

    @Autowired
    private TeacherMapper mapper;

    @Autowired
    private EmailManagerService sender;

    @Autowired
    private TeacherFinderService finder;

    @PatchMapping("/teachers/{id}/profile")
    public ResponseEntity<?> updateTeacherProfile(@PathVariable Long id,
            @RequestBody TeacherProfileDTO newTeacherProfile) throws ParseException {
        try {

            Teacher teacher = updater.updateProfile(id,
                    new FullName(newTeacherProfile.getFirstName(), newTeacherProfile.getMiddleName(),
                            newTeacherProfile.getLastName(), newTeacherProfile.getSecondSurname()),
                    new Address(newTeacherProfile.getAddress()), new Phone(newTeacherProfile.getPhone()),
                    new DateOfBirth(newTeacherProfile.getDateOfBirth()), newTeacherProfile.getBiography());

            TeacherProfileDTO profile = finder.findTeacherProfile(id);

            return new ResponseEntity<>(profile, HttpStatus.OK);
        } catch (NullFieldNotPermitted e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (WrongLength e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (TeacherNotFound e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // Implement with JWT
    @PatchMapping("/teacher/{id}/email")
    public void sendTestEmail(@PathVariable Long id) {
        this.sender.sendEmail("kurutadevelop03@gmail.com", "r.pardo03@ufromail.cl", "Buenas Noches",
                "Hola, que tal está la tarde ? :D");
    }

}
