package com.example.uetstudy.library.Service;


import com.example.uetstudy.library.DTO.SubjectDTO;
import com.example.uetstudy.library.Model.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    Subject save(Subject subject);
    Optional<Subject> getSubjectById(Long id);
    Subject update(Subject subject);
    List<Subject> findAllSubject();
    List<SubjectDTO> filterSubject(Long id);
    void deleteSubjectById(Long id);
    List<SubjectDTO> findAllSubjectDTO();
}
