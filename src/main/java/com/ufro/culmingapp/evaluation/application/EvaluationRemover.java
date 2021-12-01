package com.ufro.culmingapp.evaluation.application;

import com.ufro.culmingapp.evaluation.domain.Evaluation;
import com.ufro.culmingapp.evaluation.domain.EvaluationRepository;
import com.ufro.culmingapp.evaluation.domain.exceptions.EvaluationNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationRemover {

    @Autowired
    private EvaluationRepository repository;

    @Autowired
    private EvaluationFinderService finder;

    public void remove(Long id) throws EvaluationNotFound {
        Evaluation evaluation = finder.findById(id);
        repository.delete(evaluation);
    }
}
