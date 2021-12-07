package com.ufro.culmingapp.subject.application;

import com.ufro.culmingapp.subject.application.DTOs.SubjectDTO;
import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.subject.domain.SubjectRepository;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectFinderService {

    @Autowired
    private SubjectRepository repository;

    public Subject findById(Integer id) throws SubjectNotFound {
        Optional<Subject> subject = repository.findById(id);
        if (!subject.isPresent()) {
            throw new SubjectNotFound(id);
        }
        return subject.get();
    }

    public List<SubjectDTO> getSubjectsTakenByAStudent(Long studentId) {
        Optional<List<SubjectDTO>> subjects = repository.fetchSubjectsTakenByAStudent(studentId);
        if (subjects.isEmpty()) {
            return new ArrayList<>();
        }
        return subjects.get();
    }

    public List<Subject> getRequiredSubjects() {
        Optional<List<Subject>> subjects = repository.fetchRequiredSubjects();
        if (subjects.isEmpty()) {
            return new ArrayList<>();
        }
        return subjects.get();
    }

}
