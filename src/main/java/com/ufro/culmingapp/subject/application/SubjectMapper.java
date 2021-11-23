package com.ufro.culmingapp.subject.application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.ufro.culmingapp.subject.application.DTOs.SubjectDTO;

import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

  public List<SubjectDTO> buildSubjectDTO(String ids, String names) {
    List<String> namesL = List.of(names.split(","));
    List<String> idsList = List.of(ids.split(","));

    List<SubjectDTO> subjects = new ArrayList<>();
    IntStream.range(0, idsList.size())
        .forEach(i -> subjects.add(new SubjectDTO(Integer.parseInt(idsList.get(i)), namesL.get(i))));
    return subjects;
  }

}
