package com.ufro.culmingapp.student.infrastructure;

import com.ufro.culmingapp.evaluation.domain.exceptions.EvaluationNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.shared.domain.exceptions.GradeNotValid;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.domain.valueobjects.Grade;
import com.ufro.culmingapp.shared.domain.valueobjects.GradeDTO;
import com.ufro.culmingapp.student.application.StudentQualifier;
import com.ufro.culmingapp.student.domain.exceptions.StudentNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class StudenPostController {

    @Autowired
    private StudentQualifier qualifier;

    @PostMapping("/students/{studentId}/evaluations/{evaluationId}")
    public ResponseEntity<?> rateStudent(@PathVariable Long studentId, @PathVariable Long evaluationId,
            @RequestBody GradeDTO grade) {

        try {

            Grade newGrade = new Grade(grade.getGrade());

            qualifier.rate(studentId, evaluationId, newGrade);

            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (NullFieldNotPermitted e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        } catch (GradeNotValid e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        } catch (StudentNotFound | EvaluationNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }

    }
}
