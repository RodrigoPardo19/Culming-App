package com.ufro.culmingapp.assistance.application;

import com.ufro.culmingapp.assistance.domain.Assistance;
import com.ufro.culmingapp.assistance.domain.AssistanceDate;
import com.ufro.culmingapp.assistance.domain.AssistanceRepository;
import com.ufro.culmingapp.course.application.CourseFinderService;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.domain.valueobjects.GenerationYear;
import com.ufro.culmingapp.student.application.StudentAssistanceCreator;
import com.ufro.culmingapp.student.application.StudentFinderService;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.subject.application.SubjectFinderService;
import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AssistanceCreator {

    @Autowired
    private AssistanceRepository repository;

    @Autowired
    private SubjectFinderService subjectFinder;

    @Autowired
    private CourseFinderService courseFinder;

    @Autowired
    private StudentAssistanceCreator studentAssistanceCreator;

    @Autowired
    private StudentFinderService studentFinder;

    public void create(AssistanceDate date, Integer courseId, Integer subjectId)
            throws NullFieldNotPermitted, SubjectNotFound, CourseNotFound {

        Subject subject = subjectFinder.findById(subjectId);
        Course course = courseFinder.findById(courseId);

        GenerationYear year = getCurrentYear();

        Assistance newAssistance = new Assistance(date, year);

        newAssistance.setSubject(subject);
        newAssistance.setCourse(course);

        repository.save(newAssistance);

        List<Student> students = studentFinder.getStudendstakingASubjectInACourse(courseId, subjectId);
        studentAssistanceCreator.initStudentAssistancesToPresent(newAssistance, students);

    }

    private GenerationYear getCurrentYear() throws NullFieldNotPermitted {
        Integer currentYear = LocalDate.now().getYear();
        return new GenerationYear(currentYear);
    }
}
