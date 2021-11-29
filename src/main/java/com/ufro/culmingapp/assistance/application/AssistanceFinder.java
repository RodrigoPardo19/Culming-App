package com.ufro.culmingapp.assistance.application;

import com.ufro.culmingapp.assistance.domain.Assistance;
import com.ufro.culmingapp.assistance.domain.AssistanceRepository;
import com.ufro.culmingapp.assistance.domain.exceptions.AssistanceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssistanceFinder {

    @Autowired
    private AssistanceRepository repository;

    public Assistance findById(Long id) throws AssistanceNotFound {
        Optional<Assistance> assistance = repository.findById(id);
        if (!assistance.isPresent()) {
            throw new AssistanceNotFound(id);
        }
        return assistance.get();
    }
}
