package com.ufro.culmingapp.tutor.application;

import com.ufro.culmingapp.student.application.DTOs.StudentWithFullNameDTO;
import com.ufro.culmingapp.tutor.domain.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorFinder {

    @Autowired
    private TutorRepository repository;

    public List<StudentWithFullNameDTO> getPupils(Long id) {
        Optional<List<StudentWithFullNameDTO>> pupils = repository.fetchTutorPupils(id);
        return pupils.get();
    }
}
