package com.ufro.culmingapp.school.application;

import com.ufro.culmingapp.school.domain.School;
import com.ufro.culmingapp.school.domain.SchoolRepository;
import com.ufro.culmingapp.school.domain.exceptions.SchoolNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolFinder {

    @Autowired
    private SchoolRepository repository;


    public School findById(Long id) throws SchoolNotFound {
        Optional<School> school = repository.findById(id);
        if (!school.isPresent()) {
            throw new SchoolNotFound(id);
        }
        return school.get();
    }

}
