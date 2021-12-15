package com.ufro.culmingapp.tutor.application;

import com.ufro.culmingapp.student.application.DTOs.StudentWithCourseAndFullnameDTO;
import com.ufro.culmingapp.tutor.application.DTOs.TutorHomeDTO;
import com.ufro.culmingapp.tutor.domain.Tutor;
import com.ufro.culmingapp.tutor.domain.TutorNotFound;
import com.ufro.culmingapp.tutor.domain.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorFinder {

    @Autowired
    private TutorRepository repository;

    public Tutor findById(Long id) throws TutorNotFound {
        Optional<Tutor> tutor = repository.findById(id);
        if (!tutor.isPresent()) {
            throw new TutorNotFound(id);
        }
        return tutor.get();
    }

    public TutorHomeDTO findTutorHome(Long id) throws TutorNotFound {
        Optional<TutorHomeDTO> tutorHome = repository.fetchTutorHomeById(id);
        if (!tutorHome.isPresent()) {
            throw new TutorNotFound(id);
        }
        return tutorHome.get();
    }

    public Optional<Tutor> getByEmail(String email) {
        return repository.fetchByEmail(email);
    }

    public List<TutorWithFullNameDTO> getSchoolTutors(Long id) throws TutorNotFound {
        Optional<List<TutorWithFullNameDTO>> tutors = repository.fetchSchoolTutors(id);
        if (tutors.isEmpty()) {
            throw new TutorNotFound(id);
        }
        return tutors.get();
    }

    public TutorWithFullNameDTO getTutorWithFullName(Long id) throws TutorNotFound {
        Optional<TutorWithFullNameDTO> tutor = repository.fetchTutorWithFullName(id);
        if (tutor.isEmpty()) {
            throw new TutorNotFound(id);
        }
        return tutor.get();
    }

    public List<StudentWithCourseAndFullnameDTO> getPupils(Long id) {
        Optional<List<StudentWithCourseAndFullnameDTO>> pupils = repository.fetchTutorPupils(id);
        return pupils.get();
    }
}
