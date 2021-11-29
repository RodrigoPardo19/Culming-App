package com.ufro.culmingapp.homework.application;

import com.ufro.culmingapp.course.application.CourseFinderService;
import com.ufro.culmingapp.course.domain.Course;
import com.ufro.culmingapp.course.domain.exceptions.CourseNotFound;
import com.ufro.culmingapp.homework.domain.Homework;
import com.ufro.culmingapp.homework.domain.HomeworkDeadline;
import com.ufro.culmingapp.homework.domain.HomeworkInstruction;
import com.ufro.culmingapp.homework.domain.HomeworkRepository;
import com.ufro.culmingapp.homework.domain.exceptions.HomeworkStateNotFound;
import com.ufro.culmingapp.shared.domain.exceptions.NullFieldNotPermitted;
import com.ufro.culmingapp.shared.domain.valueobjects.GenerationYear;
import com.ufro.culmingapp.student.application.StudentFinderService;
import com.ufro.culmingapp.student.application.StudentHomeworkCreator;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.subject.application.SubjectFinderService;
import com.ufro.culmingapp.subject.domain.Subject;
import com.ufro.culmingapp.subject.domain.exceptions.SubjectNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HomeworkCreator {

    @Autowired
    private HomeworkRepository repository;

    @Autowired
    private SubjectFinderService subjectFinder;

    @Autowired
    private CourseFinderService courseFinder;

    @Autowired
    private StudentHomeworkCreator studentHomeworkCreator;

    @Autowired
    private StudentFinderService studentFinder;

    public void create(HomeworkInstruction instruction, HomeworkDeadline deadline,
                       Integer courseId, Integer subjectId)
            throws NullFieldNotPermitted, SubjectNotFound, CourseNotFound, HomeworkStateNotFound {

        Subject subject = subjectFinder.findById(subjectId);
        Course course = courseFinder.findById(courseId);
        GenerationYear year = getCurrentYear();

        Homework newHomework = new Homework(instruction, deadline, year);

        newHomework.setSubject(subject);
        newHomework.setCourse(course);

        repository.save(newHomework);

        List<Student> students = studentFinder.getStudendstakingASubjectInACourse(courseId, subjectId);
        studentHomeworkCreator.initStudentHomeworksToPendingState(newHomework, students);

    }

    private GenerationYear getCurrentYear() throws NullFieldNotPermitted {
        Integer currentYear = LocalDate.now().getYear();
        return new GenerationYear(currentYear);
    }

}
