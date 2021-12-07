package com.ufro.culmingapp.student.application;

import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.student.domain.StudentRepository;
import com.ufro.culmingapp.student.domain.exceptions.StudentNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentRemover {


    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentFinderService finder;

    public void remove(Long id) throws StudentNotFound {
        Student student = finder.findById(id);
        student.setIsActive(false);
        repository.save(student);
    }
}
