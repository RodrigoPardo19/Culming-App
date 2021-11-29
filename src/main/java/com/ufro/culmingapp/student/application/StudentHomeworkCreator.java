package com.ufro.culmingapp.student.application;

import com.ufro.culmingapp.homework.application.HomeworkFinder;
import com.ufro.culmingapp.homework.application.HomeworkStateFinder;
import com.ufro.culmingapp.homework.domain.Homework;
import com.ufro.culmingapp.homework.domain.HomeworkState;
import com.ufro.culmingapp.homework.domain.exceptions.HomeworkStateNotFound;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.student.domain.StudentHomework;
import com.ufro.culmingapp.student.domain.StudentHomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentHomeworkCreator {

    @Autowired
    private StudentHomeworkRepository repository;

    @Autowired
    private HomeworkFinder homeworkFinder;

    @Autowired
    private HomeworkStateFinder homeworkStateFinder;

    public void initStudentHomeworksToPendingState(Homework homework, List<Student> students)
            throws HomeworkStateNotFound {

        final Integer PENDING_STATE_ID = 3;

        HomeworkState state = homeworkStateFinder.findById(PENDING_STATE_ID);

        students.stream()
                .forEach(s -> {
                    StudentHomework studentHomework = new StudentHomework();
                    studentHomework.setHomework(homework);
                    studentHomework.setStudent(s);
                    studentHomework.setState(state);
                    // To do: ver si ocurre el problema de N + 1
                    repository.save(studentHomework);
                });
    }
}
