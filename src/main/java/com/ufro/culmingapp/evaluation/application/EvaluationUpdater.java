package com.ufro.culmingapp.evaluation.application;

import com.ufro.culmingapp.course.application.CourseFinderService;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.evaluation.application.DTOs.EvaluationDTO;
import com.ufro.culmingapp.evaluation.domain.Evaluation;
import com.ufro.culmingapp.evaluation.domain.EvaluationDate;
import com.ufro.culmingapp.evaluation.domain.EvaluationDescription;
import com.ufro.culmingapp.evaluation.domain.EvaluationRepository;
import com.ufro.culmingapp.evaluation.domain.exceptions.EvaluationNotFound;
import com.ufro.culmingapp.evaluationtype.application.EvaluationTypeFinder;
import com.ufro.culmingapp.evaluationtype.domain.EvaluationType;
import com.ufro.culmingapp.evaluationtype.domain.exceptions.EvaluationTypeNotFound;
import com.ufro.culmingapp.subject.application.SubjectFinderService;
import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationUpdater {

    @Autowired
    private EvaluationRepository repository;
    
    @Autowired
    private EvaluationFinderService finder;

    @Autowired
    private SubjectFinderService subjectFinder;

    @Autowired
    private CourseFinderService courseFinder;

    @Autowired
    private EvaluationTypeFinder evaluationTypeFinder;

    public EvaluationDTO update(Long id, EvaluationDescription description, EvaluationDate date,
     Integer courseId, Integer subjectId, Integer typeId)
      throws EvaluationNotFound, SubjectNotFound, CourseNotFound, EvaluationTypeNotFound  {

        Evaluation evaluation = finder.findById(id); 

        Subject subject = subjectFinder.findById(subjectId);
        Course course = courseFinder.findById(courseId);
        EvaluationType type = evaluationTypeFinder.findById(typeId); 

        
        evaluation.setDescription(description);
        evaluation.setDate(date);
        evaluation.setSubject(subject);
        evaluation.setCourse(course);
        evaluation.setType(type);

        repository.save(evaluation);

        return new EvaluationDTO(id, description.getDescription(), date);

    }
}
