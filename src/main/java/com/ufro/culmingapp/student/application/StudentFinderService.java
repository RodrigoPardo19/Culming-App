package com.ufro.culmingapp.student.application;

import com.ufro.culmingapp.student.application.DTOs.StudentEditableFieldsDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentHomeDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentMiniProfileDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithEvaluationDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithFullNameDTO;
import com.ufro.culmingapp.student.application.DTOs.StudentWithNestedEvaluationsDTO;
import com.ufro.culmingapp.student.domain.Student;
import com.ufro.culmingapp.student.domain.StudentRepository;
import com.ufro.culmingapp.student.domain.exceptions.StudentNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentFinderService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentMapper mapper;

    public Student findById(Long id) throws StudentNotFound {
        Optional<Student> student = repository.findById(id);
        if (!student.isPresent()) {
            throw new StudentNotFound(id);
        }
        return student.get();
    }

    public StudentHomeDTO findStudentHome(Long id) throws StudentNotFound {
        Optional<StudentHomeDTO> studentHome = repository.fetchStudentHomeById(id);
        if (!studentHome.isPresent()) {
            throw new StudentNotFound(id);
        }
        return studentHome.get();
    }

    public StudentEditableFieldsDTO getStudentWithEditableFields(Long schoolId, Long studentId) throws StudentNotFound {
        Optional<StudentEditableFieldsDTO> student = repository.fetchStudentWithEditableFields(schoolId, studentId);
        if (!student.isPresent()) {
            throw new StudentNotFound(studentId);
        }
        return student.get();
    }

    public Optional<Student> getByEmail(String email) {
        return repository.fetchByEmail(email);
    }

    public List<StudentWithNestedEvaluationsDTO> getEvaluationsOfStudentsOfASubjectInACourse(Integer courseId,
            Integer subjectId) {
        Optional<List<StudentWithEvaluationDTO>> studentsEvaluations = repository
                .fetchEvaluationsOfStudentsOfASubjectInACourse(courseId, subjectId);
        if (studentsEvaluations.get().isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.transformInNestedObject(studentsEvaluations.get());
    }

    public List<StudentWithNestedEvaluationsDTO> getWorkshopsOfStudentsOfASubjectInACourse(Integer courseId,
            Integer subjectId) {
        Optional<List<StudentWithEvaluationDTO>> studentsWorkshops = repository
                .fetchWorkshopsOfStudentsOfASubjectInACourse(courseId, subjectId);
        if (studentsWorkshops.get().isEmpty()) {
            return new ArrayList<>();
        }
        return mapper.transformInNestedObject(studentsWorkshops.get());
    }

    public List<Student> getStudendstakingASubjectInACourse(Integer courseId, Integer subjectId) {
        Optional<List<Student>> students = repository.fetchStudentsTakingASubjectInACourse(courseId, subjectId);
        if (students.isEmpty()) {
            return new ArrayList<>();
        }
        return students.get();
    }

    public List<StudentWithFullNameDTO> getStudentsWithFullNameTakingASubjectInACourse(Integer courseId,
            Integer subjectId) {
        Optional<List<StudentWithFullNameDTO>> students = repository.fetchStudentsWithFullNameTakingASubjectInACourse(
                courseId,
                subjectId);
        if (students.isEmpty()) {
            return new ArrayList<>();
        }
        return students.get();
    }

    public StudentWithNestedEvaluationsDTO getEvaluationsOfStudentOfASubjectInACourse(Integer courseId,
            Integer subjectId,
            Long studentId) {
        Optional<List<StudentWithEvaluationDTO>> studentsEvaluations = repository
                .fetchEvaluationOfStudentOfASubjectInACourse(courseId, subjectId, studentId);
        if (studentsEvaluations.isEmpty()) {
            return new StudentWithNestedEvaluationsDTO();
        }
        return mapper.transformInStudentWithNesteEvaluations(studentsEvaluations.get());
    }

    public StudentWithNestedEvaluationsDTO getWorkshopsOfStudentOfASubjectInACourse(Integer courseId,
            Integer subjectId,
            Long studentId) {
        Optional<List<StudentWithEvaluationDTO>> studentsEvaluations = repository
                .fetchWorkshopsOfStudentOfASubjectInACourse(courseId, subjectId, studentId);
        if (studentsEvaluations.isEmpty()) {
            return new StudentWithNestedEvaluationsDTO();
        }
        return mapper.transformInStudentWithNesteEvaluations(studentsEvaluations.get());
    }

    public List<StudentMiniProfileDTO> findSchoolStudents(Long id) throws StudentNotFound {
        Optional<List<StudentMiniProfileDTO>> students = repository
                .fetchStudentsWithMiniProfile(id);
        if (!students.isPresent()) {
            throw new StudentNotFound(id);
        }
        return students.get();
    }

}
