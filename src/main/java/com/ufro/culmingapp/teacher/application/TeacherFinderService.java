package com.ufro.culmingapp.teacher.application;

import java.util.List;
import java.util.Optional;

import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.teacher.domain.Teacher;
import com.ufro.culmingapp.teacher.domain.TeacherRepository;
import com.ufro.culmingapp.teacher.domain.exceptions.TeacherNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherFinderService {

    private TeacherRepository repository;

    @Autowired
    public TeacherFinderService(TeacherRepository repository) {
        this.repository = repository;
    }

    public Teacher findById(Long id) throws TeacherNotFound {
        Optional<Teacher> teacher = repository.findById(id);
        if (!teacher.isPresent()) {
            throw new TeacherNotFound(id);
        }
        return teacher.get();
    }

    public Teacher findByEmail(String email) throws TeacherNotFound {
        Optional<Teacher> teacher = repository.findByEmail(email);
        if (teacher.isPresent()) {
            return teacher.get();
        } else {
            throw new TeacherNotFound(email);
        }
    }

    public List<Subject> getSubjects(Long id) throws TeacherNotFound {
        Optional<Teacher> teacher = repository.findById(id);
        if (teacher.isPresent()) {
            return teacher.get().getSubjects();
        } else {
            throw new TeacherNotFound(id);
        }
    }

}
