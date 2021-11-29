package com.ufro.culmingapp.student.application;

import com.ufro.culmingapp.assistance.domain.Assistance;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.student.domain.StudentAssistance;
import com.ufro.culmingapp.student.domain.StudentAssistanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAssistanceCreator {

    @Autowired
    private StudentAssistanceRepository repository;

    public void initStudentAssistancesToPresent(Assistance assistance, List<Student> students) {

        students.forEach(s -> {
            StudentAssistance studentAssistance = new StudentAssistance(s, assistance, true);
            // To do: ver si ocurre el problema de N + 1
            repository.save(studentAssistance);
        });
    }
}
