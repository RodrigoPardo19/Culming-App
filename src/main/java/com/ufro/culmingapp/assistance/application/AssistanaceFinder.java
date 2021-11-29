package com.ufro.culmingapp.assistance.application;

import com.ufro.culmingapp.assistance.application.DTOs.AssistanceDTO;
import com.ufro.culmingapp.assistance.domain.Assistance;
import com.ufro.culmingapp.assistance.domain.AssistanceRepository;
import com.ufro.culmingapp.assistance.domain.exceptions.AssistanceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AssistanaceFinder {

    @Autowired
    private AssistanceRepository repository;

    public Assistance findById(Long id) throws AssistanceNotFound {
        Optional<Assistance> assistance = repository.findById(id);
        if (!assistance.isPresent()) {
            throw new AssistanceNotFound(id);
        }
        return assistance.get();
    }

    public List<AssistanceDTO> getAssistancesOfASubjectInACouseByMonth(Integer courseId, Integer subjectId,
                                                                       Integer month,
                                                                       Integer year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        Optional<List<AssistanceDTO>> assistances = repository
                .fetchAssistancesOfASubjectInACourseByMonth(courseId, subjectId, start, end);
        return assistances.get();
    }
}
