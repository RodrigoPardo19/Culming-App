package com.ufro.culmingapp.evaluation.infrastructure;

import java.text.ParseException;

import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.evaluation.application.EvaluationCreator;
import com.ufro.culmingapp.evaluation.application.DTOs.EvaluationWithSubjectWithCourseWithTypeDTO;
import com.ufro.culmingapp.evaluation.domain.EvaluationDate;
import com.ufro.culmingapp.evaluation.domain.EvaluationDescription;
import com.ufro.culmingapp.evaluationtype.domain.exceptions.EvaluationTypeNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvaluationPostController {

    @Autowired
    private EvaluationCreator creator;

    @PostMapping("/evaluation")
    public ResponseEntity<?> createNewEvaluation(
            @RequestBody EvaluationWithSubjectWithCourseWithTypeDTO newEvaluation) {

        try {

            EvaluationDescription description = new EvaluationDescription(newEvaluation.getDescription());
            EvaluationDate date = new EvaluationDate(newEvaluation.getDate());
            Integer subjectId = newEvaluation.getSubjectId();
            Integer courseId = newEvaluation.getCourseId();
            Integer typeId = newEvaluation.getTypeId();

            creator.create(description, date, courseId, subjectId, typeId);

            return ResponseEntity.status(HttpStatus.CREATED).build();

        } catch (NullFieldNotPermitted e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        } catch (SubjectNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        } catch (CourseNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        } catch (EvaluationTypeNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }

    }

}
