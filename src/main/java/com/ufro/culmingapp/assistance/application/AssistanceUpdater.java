package com.ufro.culmingapp.assistance.application;

import com.ufro.culmingapp.assistance.application.DTOs.AssistanceDTO;
import com.ufro.culmingapp.assistance.domain.Assistance;
import com.ufro.culmingapp.assistance.domain.AssistanceDate;
import com.ufro.culmingapp.assistance.domain.AssistanceRepository;
import com.ufro.culmingapp.assistance.domain.exceptions.AssistanceNotFound;
import com.ufro.culmingapp.course.application.CourseFinderService;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.subject.application.SubjectFinderService;
import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssistanceUpdater {

    @Autowired
    private AssistanceRepository repository;

    @Autowired
    private AssistanceFinder finder;

    @Autowired
    private SubjectFinderService subjectFinder;

    @Autowired
    private CourseFinderService courseFinder;

    public AssistanceDTO update(Long id, AssistanceDate date, Integer courseId, Integer subjectId)
            throws AssistanceNotFound, SubjectNotFound, CourseNotFound {

        Assistance assistance = finder.findById(id);

        Subject subject = subjectFinder.findById(subjectId);
        Course course = courseFinder.findById(courseId);

        assistance.setDate(date);
        assistance.setSubject(subject);
        assistance.setCourse(course);

        repository.save(assistance);

        return new AssistanceDTO(id, date);

    }
}
