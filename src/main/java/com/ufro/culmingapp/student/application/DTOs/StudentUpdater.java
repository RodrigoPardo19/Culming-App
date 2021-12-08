package com.ufro.culmingapp.student.application.DTOs;

import com.ufro.culmingapp.shared.domain.valueobjects.Address;
import com.ufro.culmingapp.shared.domain.valueobjects.DateOfBirth;
import com.ufro.culmingapp.shared.domain.valueobjects.EnrollmentDate;
import com.ufro.culmingapp.shared.domain.valueobjects.FullName;
import com.ufro.culmingapp.student.application.StudentFinderService;
import com.ufro.culmingapp.student.application.StudentMapper;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.student.domain.StudentRepository;
import com.ufro.culmingapp.student.domain.exceptions.StudentNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentUpdater {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentFinderService finder;

    @Autowired
    private StudentMapper mapper;

    public StudentWithoutCourseAndTutorDTO update(Long id, FullName fullName, Address address, DateOfBirth dateOfBirth,
                                                  EnrollmentDate enrollmentDate) throws StudentNotFound {
        Student student = finder.findById(id);
        student.setFullName(fullName);
        student.setAddress(address);
        student.setDateOfBirth(dateOfBirth);
        student.setEnrollmentDate(enrollmentDate);
        repository.save(student);
        return mapper.mapToStudentDTOWithoutCourseAndTutor(student);
    }
}
