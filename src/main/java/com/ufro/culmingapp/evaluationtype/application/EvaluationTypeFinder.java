package com.ufro.culmingapp.evaluationtype.application;

import java.util.Optional;

import com.ufro.culmingapp.evaluationtype.domain.EvaluationType;
import com.ufro.culmingapp.evaluationtype.domain.EvaluationTypeRepository;
import com.ufro.culmingapp.evaluationtype.domain.exceptions.EvaluationTypeNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationTypeFinder {

    @Autowired
    private EvaluationTypeRepository repository;

    public EvaluationType findById(Integer id) throws EvaluationTypeNotFound {
        Optional<EvaluationType> type = repository.findById(id);
        if(!type.isPresent()) {
            throw new EvaluationTypeNotFound(id);
        }
        return type.get();
    }
    
}
