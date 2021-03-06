package com.ufro.culmingapp.homework.application;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ufro.culmingapp.homework.application.DTOs.HomeworkDTO;
import com.ufro.culmingapp.homework.domain.Homework;
import com.ufro.culmingapp.homework.domain.HomeworkRepository;
import com.ufro.culmingapp.homework.domain.exceptions.HomeworkNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeworkFinder {

    @Autowired
    private HomeworkRepository repository;

    public Homework findById(Long id) throws HomeworkNotFound {
        Optional<Homework> homework = repository.findById(id);
        if(!homework.isPresent()) {
            throw new HomeworkNotFound(id);
        }
        return homework.get();
    }

    public List<HomeworkDTO> getHomeworksOfASubjectInACouseByMonth(Integer courseId, Integer subjectId, Integer month,
            Integer year) {
    
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        Optional<List<HomeworkDTO>> homeworks = repository
                .fetchHomeworksOfASubjectInACourseByMonth(courseId, subjectId, start, end);
        return homeworks.get();
    }

}
