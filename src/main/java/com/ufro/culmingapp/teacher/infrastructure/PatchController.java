package com.ufro.culmingapp.teacher.infrastructure;

import com.ufro.culmingapp.shared.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.exceptions.WrongEmailFormat;
import com.ufro.culmingapp.teacher.application.TeacherFinderService;
import com.ufro.culmingapp.teacher.application.TeacherUpdaterService;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.teacher.domain.exceptions.TeacherNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatchController {

    @Autowired
    private TeacherUpdaterService updater;

    @Autowired
    private TeacherFinderService finder;

    // Actualiza solo los campos que el profesor puede modificar
    @PatchMapping("/teachers/{id}")
    public ResponseEntity<?> updateTeacherValues(@PathVariable Long id, @RequestBody Teacher teacherUpdated) {
        try {
            Teacher teacher = finder.findById(id);
            updater.updateTeacherValues(teacher, teacherUpdated);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/teachers/{id}/address")
    public ResponseEntity<?> changeAddress(@PathVariable Long id, @RequestBody Teacher address) {
        try {
            Teacher teacher = finder.findById(id);
            Teacher teacherUpdated = updater.changeAddress(teacher, address.getAddress());
            return ResponseEntity.status(HttpStatus.OK).body(teacherUpdated);
        } catch (TeacherNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NullFieldNotPermitted e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/teachers/{id}/phone")
    public ResponseEntity<?> changePhone(@PathVariable Long id, @RequestBody Teacher phone) {
        try {
            Teacher teacher = finder.findById(id);
            Teacher teacherUpdated = updater.changePhone(teacher, phone.getPhone());
            return ResponseEntity.status(HttpStatus.OK).body(teacherUpdated);
        } catch (TeacherNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NullFieldNotPermitted e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/teachers/{id}/email")
    public ResponseEntity<?> changeEmail(@PathVariable Long id, @RequestBody Teacher email) {
        try {
            Teacher teacher = finder.findById(id);
            Teacher teacherUpdated = updater.changeEmail(teacher, email.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(teacherUpdated);
        } catch (TeacherNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NullFieldNotPermitted | WrongEmailFormat e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
