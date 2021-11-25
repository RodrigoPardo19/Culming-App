package com.ufro.culmingapp.evaluation.infrastructure;

import java.text.ParseException;

import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.evaluation.application.EvaluationUpdater;
import com.ufro.culmingapp.evaluation.application.DTOs.EvaluationDTO;
import com.ufro.culmingapp.evaluation.application.DTOs.EvaluationWithSubjectWithCourseWithTypeDTO;
import com.ufro.culmingapp.evaluation.domain.EvaluationDate;
import com.ufro.culmingapp.evaluation.domain.EvaluationDescription;
import com.ufro.culmingapp.evaluation.domain.exceptions.EvaluationNotFound;
import com.ufro.culmingapp.evaluationtype.domain.exceptions.EvaluationTypeNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class EvaluationPatchController {

    @Autowired
    private EvaluationUpdater updater;

    @PatchMapping("/evaluation/{id}")
    public ResponseEntity<?> createNewEvaluation(@PathVariable Long id,
            @RequestBody EvaluationWithSubjectWithCourseWithTypeDTO evaluation) {

        try {

            EvaluationDescription description = new EvaluationDescription(evaluation.getDescription());
            EvaluationDate date = new EvaluationDate(evaluation.getDate());
            Integer subjectId = evaluation.getSubjectId();
            Integer courseId = evaluation.getCourseId();
            Integer typeId = evaluation.getTypeId();

            EvaluationDTO evaluationUpdated = updater.update(id, description, date, courseId, subjectId, typeId);

            // To do buscar http response para las modificaciones
            return ResponseEntity.status(HttpStatus.CREATED).body(evaluationUpdated);

        } catch (NullFieldNotPermitted e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        } catch (EvaluationNotFound | SubjectNotFound | CourseNotFound | EvaluationTypeNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }

    }

}
