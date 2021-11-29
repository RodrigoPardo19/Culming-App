package com.ufro.culmingapp.homework.application;

import java.util.Optional;

import com.ufro.culmingapp.homework.domain.HomeworkState;
import com.ufro.culmingapp.homework.domain.HomeworkStateRepository;
import com.ufro.culmingapp.homework.domain.exceptions.HomeworkStateNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeworkStateFinder {

    @Autowired
    private HomeworkStateRepository repository;

    public HomeworkState findById(Integer id) throws HomeworkStateNotFound {
        Optional<HomeworkState> state = repository.findById(id);
        if(!state.isPresent()) {
            throw new HomeworkStateNotFound(id);
        }
        return state.get();
    }
    
}
