package com.ufro.culmingapp.course.application.DTOs;

import java.util.List;

import com.ufro.culmingapp.subject.application.DTOs.SubjectDTO;

import org.springframework.beans.factory.annotation.Value;

public interface CourseWithSubjectDTO {

    Long getId();

    String getName();

    @Value("#{@subjectMapper.buildSubjectDTO(target.subjectIds, target.subjects)}")
    List<SubjectDTO> getSubjects();

}
