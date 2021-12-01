package com.ufro.culmingapp.homework.infrastructure;

import com.ufro.culmingapp.homework.application.HomeworkRemover;
import com.ufro.culmingapp.homework.domain.exceptions.HomeworkNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class HomeworkDeleteController {

    @Autowired
    private HomeworkRemover remover;

    @DeleteMapping("/homeworks/{id}")
    public ResponseEntity<?> deleteHomework(@PathVariable Long id) {
        try {
            remover.remove(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (HomeworkNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }
}
