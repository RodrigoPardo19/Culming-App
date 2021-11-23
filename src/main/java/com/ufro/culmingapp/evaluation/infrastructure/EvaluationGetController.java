package com.ufro.culmingapp.evaluation.infrastructure;

import java.util.List;

import com.ufro.culmingapp.evaluation.application.EvaluationFinderService;
import com.ufro.culmingapp.evaluation.application.DTOs.EvaluationDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvaluationGetController {

    @Autowired
    private EvaluationFinderService finder;

    @GetMapping("/courses/{courseId}/subjects/{subjectId}/evaluations")
    public ResponseEntity<?> getEvaluationsOfASubjectInACourse(@PathVariable Integer courseId,
            @PathVariable Integer subjectId) {
       List<EvaluationDTO> evaluations = finder.getEvaluationOfASubjectInACourse(courseId, subjectId);
       return ResponseEntity.status(HttpStatus.OK).body(evaluations);
    }

    @GetMapping("/courses/{courseId}/subjects/{subjectId}/workshops")
    public ResponseEntity<?> getWorkshopsOfASubjectInACourse(@PathVariable Integer courseId,
            @PathVariable Integer subjectId) {
       List<EvaluationDTO> evaluations = finder.getWorkshopsOfASubjectInACourse(courseId, subjectId);
       return ResponseEntity.status(HttpStatus.OK).body(evaluations);
    }

}
