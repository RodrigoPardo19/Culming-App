package com.ufro.culmingapp.evaluation.infrastructure;

import com.ufro.culmingapp.evaluation.application.EvaluationRemover;
import com.ufro.culmingapp.evaluation.domain.exceptions.EvaluationNotFound;
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
public class EvaluationDeleteController {

    @Autowired
    private EvaluationRemover remover;

    @DeleteMapping("/evaluation/{id}")
    public ResponseEntity<?> deleteEvaluation(@PathVariable Long id) {
        try {
            remover.remove(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EvaluationNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }
}
