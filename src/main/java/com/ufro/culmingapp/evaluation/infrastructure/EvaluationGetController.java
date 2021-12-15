package com.ufro.culmingapp.evaluation.infrastructure;

import com.ufro.culmingapp.evaluation.application.DTOs.EvaluationDTO;
import com.ufro.culmingapp.evaluation.application.EvaluationFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class EvaluationGetController {

    @Autowired
    private EvaluationFinderService finder;

    @GetMapping("/courses/{courseId}/subjects/{subjectId}/evaluations")
    @Secured({ "ROLE_TEACHER", "ROLE_STUDENT", "ROLE_TUTOR" })
    public ResponseEntity<?> getEvaluationsOfASubjectInACourse(@PathVariable Integer courseId,
            @PathVariable Integer subjectId) {
        List<EvaluationDTO> evaluations = finder.getEvaluationOfASubjectInACourse(courseId, subjectId);
        return ResponseEntity.status(HttpStatus.OK).body(evaluations);
    }

    @GetMapping("/courses/{courseId}/subjects/{subjectId}/workshops")
    @Secured({ "ROLE_TEACHER", "ROLE_STUDENT", "ROLE_TUTOR" })
    public ResponseEntity<?> getWorkshopsOfASubjectInACourse(@PathVariable Integer courseId,
            @PathVariable Integer subjectId) {
        List<EvaluationDTO> evaluations = finder.getWorkshopsOfASubjectInACourse(courseId, subjectId);
        return ResponseEntity.status(HttpStatus.OK).body(evaluations);
    }

}
