package com.ufro.culmingapp.assistance.application;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ufro.culmingapp.assistance.application.DTOs.AssistanceDTO;
import com.ufro.culmingapp.assistance.domain.AssistanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssistanaceFinder {
    
    @Autowired
    private AssistanceRepository repository;

    public List<AssistanceDTO> getAssistancesOfASubjectInACouseByMonth(Integer courseId, Integer subjectId, Integer month,
            Integer year) {
    
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        Optional<List<AssistanceDTO>> assistances = repository
                .fetchAssistancesOfASubjectInACourseByMonth(courseId, subjectId, start, end);
        return assistances.get();
    }
}
