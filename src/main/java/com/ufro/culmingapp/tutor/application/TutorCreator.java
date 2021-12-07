package com.ufro.culmingapp.tutor.application;

import com.ufro.culmingapp.shared.domain.valueobjects.Address;
import com.ufro.culmingapp.shared.domain.valueobjects.Email;
import com.ufro.culmingapp.shared.domain.valueobjects.FullName;
import com.ufro.culmingapp.tutor.application.DTOs.TutorDTO;
import com.ufro.culmingapp.tutor.domain.Tutor;
import com.ufro.culmingapp.tutor.domain.TutorAge;
import com.ufro.culmingapp.tutor.domain.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorCreator {

    @Autowired
    private TutorRepository repository;

    @Autowired
    private TutorMapper mapper;

    public TutorDTO create(FullName fullName, TutorAge age, Email email, Address address) {
        Tutor tutor = new Tutor(fullName, age, email, address);
        repository.save(tutor);
        return mapper.mapToTutorDTO(tutor);
    }
}
