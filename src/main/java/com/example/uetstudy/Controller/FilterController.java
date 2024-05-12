package com.example.uetstudy.Controller;

import com.example.uetstudy.library.DTO.DocumentDTO;
import com.example.uetstudy.library.DTO.KnowledgeBlockDTO;
import com.example.uetstudy.library.DTO.SubjectDTO;
import com.example.uetstudy.library.Service.DocumentService;
import com.example.uetstudy.library.Service.KnowledgeBlockService;
import com.example.uetstudy.library.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilterController {
    @Autowired
    private KnowledgeBlockService knowledgeBlockService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private DocumentService documentService;
    @GetMapping("/findByDepartment")
    public List<KnowledgeBlockDTO> getKnowledgeBlockByDepartment(@RequestParam("idDepartment") Long id) {
        return knowledgeBlockService.findByDepartment(id);
    }
    @GetMapping("/filterSubject")
    public List<SubjectDTO> getSubjectByKnowledgeId(@RequestParam("knowledgeId") Long id) {
        return subjectService.filterSubject(id);
    }
    @GetMapping("/findDocument")
    public List<DocumentDTO> findDocumentBySubject(@RequestParam("subjectId") Long subjectID){
        return documentService.findDocumentBySubject(subjectID);
    }
    @GetMapping("/findDocumentByName")
    public List<DocumentDTO> findDocumentByName(@RequestParam("keyword") String keyword){
        return documentService.findDocumentByName(keyword);
    }
}
