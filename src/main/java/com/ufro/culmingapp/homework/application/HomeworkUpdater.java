package com.ufro.culmingapp.homework.application;

import com.ufro.culmingapp.course.application.CourseFinderService;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.homework.application.DTOs.HomeworkDTO;
import com.ufro.culmingapp.homework.domain.Homework;
import com.ufro.culmingapp.homework.domain.HomeworkDeadline;
import com.ufro.culmingapp.homework.domain.HomeworkInstruction;
import com.ufro.culmingapp.homework.domain.HomeworkRepository;
import com.ufro.culmingapp.homework.domain.exceptions.HomeworkNotFound;
import com.ufro.culmingapp.subject.application.SubjectFinderService;
import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeworkUpdater {

    @Autowired
    private HomeworkRepository repository;
    
    @Autowired
    private HomeworkFinder finder;

    @Autowired
    private SubjectFinderService subjectFinder;

    @Autowired
    private CourseFinderService courseFinder;

    public HomeworkDTO update(Long id, HomeworkInstruction instruction, HomeworkDeadline deadline,
     Integer courseId, Integer subjectId)
      throws HomeworkNotFound, SubjectNotFound, CourseNotFound {

        Homework homework = finder.findById(id); 

        Subject subject = subjectFinder.findById(subjectId);
        Course course = courseFinder.findById(courseId);
        
        homework.setInstruction(instruction);
        homework.setDeadline(deadline);
        homework.setSubject(subject);
        homework.setCourse(course);

        repository.save(homework);

        return new HomeworkDTO(id, instruction.getInstruction(), deadline);

    }
    
}
