package com.ufro.culmingapp.evaluation.application;

import java.util.List;
import java.util.Optional;

import com.ufro.culmingapp.evaluation.application.DTOs.EvaluationDTO;
import com.ufro.culmingapp.evaluation.domain.EvaluationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationFinderService {

    @Autowired
    private EvaluationRepository repository;

    public List<EvaluationDTO> getEvaluationOfASubjectInACourse(Integer courseId,
            Integer subjectId) {
        Optional<List<EvaluationDTO>> evaluations = repository
                .fetchEvaluationOfASubjectInACourse(courseId, subjectId);
        return evaluations.get();
    }

    public List<EvaluationDTO> getWorkshopsOfASubjectInACourse(Integer courseId,
            Integer subjectId) {
        Optional<List<EvaluationDTO>> evaluations = repository
                .fetchWorkshopsOfASubjectInACourse(courseId, subjectId);
        return evaluations.get();
    }
}
