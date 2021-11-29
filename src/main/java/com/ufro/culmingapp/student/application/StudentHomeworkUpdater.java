package com.ufro.culmingapp.student.application;

import com.ufro.culmingapp.homework.application.DTOs.HomeworkStatusDTO;
import com.ufro.culmingapp.homework.application.HomeworkFinder;
import com.ufro.culmingapp.homework.application.HomeworkStateFinder;
import com.ufro.culmingapp.homework.domain.Homework;
import com.ufro.culmingapp.homework.domain.HomeworkState;
import com.ufro.culmingapp.homework.domain.exceptions.HomeworkNotFound;
import com.ufro.culmingapp.homework.domain.exceptions.HomeworkStateNotFound;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.student.domain.StudentHomework;
import com.ufro.culmingapp.student.domain.StudentHomeworkRepository;
import com.ufro.culmingapp.student.domain.exceptions.StudentHomeworkNotFound;
import com.ufro.culmingapp.student.domain.exceptions.StudentNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentHomeworkUpdater {

    @Autowired
    private StudentHomeworkRepository repository;

    @Autowired
    private StudentFinderService studentFinder;

    @Autowired
    private HomeworkFinder homeworkFinder;

    @Autowired
    private HomeworkStateFinder homeworkStateFinder;

    public HomeworkStatusDTO changeStateOfHomework(Long studentId, Long homeworkId, Integer stateId)
            throws StudentNotFound, HomeworkNotFound, HomeworkStateNotFound, StudentHomeworkNotFound {

        Student student = studentFinder.findById(studentId);
        Homework homework = homeworkFinder.findById(homeworkId);
        HomeworkState state = homeworkStateFinder.findById(stateId);

        Optional<StudentHomework> studentHomework = repository.findByStudentIdAndHomeworkId(studentId, homeworkId);

        if (!studentHomework.isPresent()) {
            throw new StudentHomeworkNotFound(studentId, homeworkId);
        }

        StudentHomework homeworkUpdated = studentHomework.get();

        homeworkUpdated.setState(state);

        repository.save(homeworkUpdated);

        return new HomeworkStatusDTO(homeworkId, state.getName());
    }
}
