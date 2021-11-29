package com.ufro.culmingapp.student.application;

import com.ufro.culmingapp.assistance.application.AssistanaceFinder;
import com.ufro.culmingapp.assistance.domain.Assistance;
import com.ufro.culmingapp.assistance.domain.exceptions.AssistanceNotFound;
import com.ufro.culmingapp.student.application.DTOs.StudentAssistanceDTO;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.student.domain.StudentAssistance;
import com.ufro.culmingapp.student.domain.StudentAssistanceRepository;
import com.ufro.culmingapp.student.domain.exceptions.StudentAssistanceNotFound;
import com.ufro.culmingapp.student.domain.exceptions.StudentNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentAssistanceUpdater {

    @Autowired
    private StudentAssistanceRepository repository;

    @Autowired
    private StudentFinderService studentFinder;

    @Autowired
    private AssistanaceFinder assistanaceFinder;

    public StudentAssistanceDTO changeStudentAssistanceState(Long studentId, Long assistanceId, Boolean isPresent)
            throws StudentNotFound, AssistanceNotFound, StudentAssistanceNotFound {

        Student student = studentFinder.findById(studentId);
        Assistance assistance = assistanaceFinder.findById(assistanceId);

        Optional<StudentAssistance> studentAssistance = repository.findByStudentIdAndAssistanceId(studentId,
                assistanceId);

        if (!studentAssistance.isPresent()) {
            throw new StudentAssistanceNotFound(studentId, assistanceId);
        }

        StudentAssistance assistanceUpdated = studentAssistance.get();

        assistanceUpdated.setIsPresent(isPresent);

        repository.save(assistanceUpdated);

        return new StudentAssistanceDTO(assistanceId, assistanceUpdated.getIsPresent());
    }
}
