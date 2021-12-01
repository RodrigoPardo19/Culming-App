package com.ufro.culmingapp.homework.application;

import com.ufro.culmingapp.homework.domain.Homework;
import com.ufro.culmingapp.homework.domain.HomeworkRepository;
import com.ufro.culmingapp.homework.domain.exceptions.HomeworkNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeworkRemover {

    @Autowired
    private HomeworkRepository repository;

    @Autowired
    private HomeworkFinder finder;

    public void remove(Long id) throws HomeworkNotFound {
        Homework homework = finder.findById(id);
        repository.delete(homework);
    }
}
