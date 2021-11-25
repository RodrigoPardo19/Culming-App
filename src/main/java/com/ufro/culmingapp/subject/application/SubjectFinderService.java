package com.ufro.culmingapp.subject.application;

import java.util.Optional;

import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.subject.domain.SubjectRepository;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectFinderService {

    @Autowired
    private SubjectRepository repository;

    public Subject findById(Integer id) throws SubjectNotFound {
        Optional<Subject> subject = repository.findById(id);
        if(!subject.isPresent()) {
            throw new SubjectNotFound(id);
        }
        return subject.get();
    }


}
