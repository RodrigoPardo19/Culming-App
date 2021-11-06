package com.ufro.culmingapp.subject.application;

import com.ufro.culmingapp.subject.domain.SubjectRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class SubjectFinderService {

    private SubjectRepository repository;

    @Autowired
    public SubjectFinderService(SubjectRepository repository) {
        this.repository = repository;
    }


}
