package com.ufro.culmingapp.teacher.application;

import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.teacher.domain.TeacherRepository;
import com.ufro.culmingapp.teacher.domain.exceptions.TeacherNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherRemover {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private TeacherFinderService finder;

    public void remove(Long id) throws TeacherNotFound {
        Teacher teacher = finder.findById(id);
        teacher.setActive(false);
        repository.save(teacher);
    }
}
