package com.ufro.culmingapp.student.infrastructure;

import com.ufro.culmingapp.assistance.domain.exceptions.AssistanceNotFound;
import com.ufro.culmingapp.evaluation.domain.exceptions.EvaluationNotFound;
import com.ufro.culmingapp.homework.application.DTOs.HomeworkStatusDTO;
import com.ufro.culmingapp.homework.domain.exceptions.HomeworkNotFound;
import com.ufro.culmingapp.homework.domain.exceptions.HomeworkStateNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.ErrorDTO;
import com.ufro.culmingapp.shared.domain.exceptions.GradeNotValid;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.domain.valueobjects.Grade;
import com.ufro.culmingapp.shared.domain.valueobjects.GradeDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentAssistanceDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO;
import com.ufro.culmingapp.student.application.StudentAssistanceUpdater;
import com.ufro.culmingapp.student.application.StudentHomeworkUpdater;
import com.ufro.culmingapp.student.application.StudentQualifier;
import com.ufro.culmingapp.student.application.StudentRemover;
import com.ufro.culmingapp.student.domain.exceptions.StudentAssistanceNotFound;
import com.ufro.culmingapp.student.domain.exceptions.StudentEvaluationNotFound;
import com.ufro.culmingapp.student.domain.exceptions.StudentHomeworkNotFound;
import com.ufro.culmingapp.student.domain.exceptions.StudentNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
public class StudentPatchController {

    @Autowired
    private StudentQualifier qualifier;

    @Autowired
    private StudentHomeworkUpdater studentHomeworkUpdater;

    @Autowired
    private StudentAssistanceUpdater studentAssistanceUpdater;

    @Autowired
    private StudentRemover remover;

    @PatchMapping("/students/{studentId}/evaluations/{evaluationId}")
    public ResponseEntity<?> rateStudent(@PathVariable Long studentId, @PathVariable Long evaluationId,
                                         @RequestBody GradeDTO grade) {
        try {

            Grade newGrade = new Grade(grade.getGrade());

            StudentWithEvaluationDTO evaluation = qualifier.changeGrade(studentId, evaluationId, newGrade);

            return ResponseEntity.status(HttpStatus.CREATED).body(evaluation);

        } catch (NullFieldNotPermitted | GradeNotValid | StudentNotFound | EvaluationNotFound
                | StudentEvaluationNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }

    @PatchMapping("/students/{studentId}/homeworks/{homeworkId}/state/{stateId}")
    public ResponseEntity<?> changetStateOfStudentHomework(@PathVariable Long studentId, @PathVariable Long homeworkId
            , @PathVariable Integer stateId) {
        try {

            HomeworkStatusDTO newHomeworkStatus = studentHomeworkUpdater.changeStateOfHomework(studentId,
                    homeworkId, stateId);

            return ResponseEntity.status(HttpStatus.CREATED).body(newHomeworkStatus);

        } catch (StudentNotFound | HomeworkNotFound | HomeworkStateNotFound
                | StudentHomeworkNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }

    @PatchMapping("/students/{studentId}/assistances/{assistanceId}/is-present/{isPresent}")
    public ResponseEntity<?> changetStateOfStudentHomework(@PathVariable Long studentId,
                                                           @PathVariable Long assistanceId,
                                                           @PathVariable Boolean isPresent) {
        try {
            StudentAssistanceDTO studentAssistanceChanged =
                    studentAssistanceUpdater.changeStudentAssistanceState(studentId,
                            assistanceId, isPresent);

            return ResponseEntity.status(HttpStatus.CREATED).body(studentAssistanceChanged);

        } catch (StudentNotFound | AssistanceNotFound | StudentAssistanceNotFound e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
        }
    }

    @PatchMapping("/students/{id}/remove")
    public ResponseEntity<?> removeStudent(@PathVariable Long id) {
        try {
            remover.remove(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        } catch (StudentNotFound e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
