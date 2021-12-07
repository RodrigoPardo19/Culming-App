package com.ufro.culmingapp.student.application;

import com.ufro.culmingapp.shared.domain.valueobjects.GenerationYear;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.student.domain.exceptions.StudentNotFound;
import com.ufro.culmingapp.studentsubject.domain.StudentSubject;
import com.ufro.culmingapp.studentsubject.domain.StudentSubjectRepository;
import com.ufro.culmingapp.subject.application.SubjectFinderService;
import com.ufro.culmingapp.subject.domain.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSubjectRegister {

    @Autowired
    private StudentSubjectRepository repository;

    @Autowired
    private SubjectFinderService subjectFinder;

    @Autowired
    private StudentFinderService studentFinder;

    public void registerRequiredSubjects(Long studentId, GenerationYear year) throws StudentNotFound {
        Student student = studentFinder.findById(studentId);
        List<Subject> subjects = subjectFinder.getRequiredSubjects();
        subjects.forEach(subject -> {
            StudentSubject studentSubject = new StudentSubject(year);
            studentSubject.setStudent(student);
            studentSubject.setSubject(subject);
            repository.save(studentSubject);
        });
    }
}
