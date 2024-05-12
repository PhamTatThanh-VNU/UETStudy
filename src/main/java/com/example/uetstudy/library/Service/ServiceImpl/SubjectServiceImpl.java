package com.example.uetstudy.library.Service.ServiceImpl;

import com.example.uetstudy.library.DTO.SubjectDTO;
import com.example.uetstudy.library.Model.Subject;
import com.example.uetstudy.library.Repository.SubjectRepository;
import com.example.uetstudy.library.Service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private final SubjectRepository subjectRepository;
    @Override
    public List<Subject> findAllSubject(){
        return subjectRepository.findAll();
    }
    @Override
    public List<SubjectDTO> filterSubject(Long id){
        return subjectRepository.filterSubject(id);
    }

    @Override
    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    //Create new Subject
    @Override
    public Subject save(Subject subject) {
        Subject subjectTemp  = new Subject();
        subjectTemp.setSubjectName(subject.getSubjectName());
        subjectTemp.setKnowledgeBlock(subject.getKnowledgeBlock());
        return subjectRepository.save(subjectTemp);
    }
    //Update Subject
    @Override
    public Subject update(Subject subject) {
        Subject subjectUpdate = subjectRepository.getReferenceById(subject.getSubjectId());
        subjectUpdate.setSubjectName(subject.getSubjectName());
        subjectUpdate.setKnowledgeBlock(subject.getKnowledgeBlock());
        return subjectRepository.save(subjectUpdate);
    }

    @Override
    public List<SubjectDTO> findAllSubjectDTO() {
        return subjectRepository.findAllSubjectDTO();
    }

    @Override
    public void deleteSubjectById(Long id) {
        try {
            subjectRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Can't delete subject, you have to delete every document with this subject id.");
        } catch (Exception e) {
            throw new RuntimeException("Can't delete subject", e);
        }
    }
}
