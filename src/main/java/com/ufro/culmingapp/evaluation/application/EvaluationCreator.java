package com.ufro.culmingapp.evaluation.application;

import java.time.LocalDate;

import com.ufro.culmingapp.course.application.CourseFinderService;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.evaluation.domain.Evaluation;
import com.ufro.culmingapp.evaluation.domain.EvaluationDate;
import com.ufro.culmingapp.evaluation.domain.EvaluationDescription;
import com.ufro.culmingapp.evaluation.domain.EvaluationRepository;
import com.ufro.culmingapp.evaluationtype.application.EvaluationTypeFinder;
import com.ufro.culmingapp.evaluationtype.domain.EvaluationType;
import com.ufro.culmingapp.evaluationtype.domain.exceptions.EvaluationTypeNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.domain.valueobjects.GenerationYear;
import com.ufro.culmingapp.subject.application.SubjectFinderService;
import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluationCreator {

    @Autowired
    private EvaluationRepository repository;

    @Autowired
    private SubjectFinderService subjectFinder;

    @Autowired
    private CourseFinderService courseFinder;

    @Autowired
    private EvaluationTypeFinder evaluationTypeFinder;

    public void create(EvaluationDescription description, EvaluationDate date,
     Integer courseId, Integer subjectId, Integer typeId)
      throws NullFieldNotPermitted, SubjectNotFound, CourseNotFound, EvaluationTypeNotFound {

        Subject subject = subjectFinder.findById(subjectId);
        Course course = courseFinder.findById(courseId);
        EvaluationType type = evaluationTypeFinder.findById(typeId); 

        GenerationYear year = getCurrentYear();

        Evaluation newEvaluation = new Evaluation(description, date, year);

        newEvaluation.setSubject(subject);
        newEvaluation.setCourse(course);
        newEvaluation.setType(type);

        repository.save(newEvaluation);

    }

    private GenerationYear getCurrentYear() throws NullFieldNotPermitted {
        Integer currentYear = LocalDate.now().getYear();
        return new GenerationYear(currentYear);
    }
    
}
