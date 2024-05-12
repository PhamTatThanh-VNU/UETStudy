package com.example.uetstudy.Controller;

import com.example.uetstudy.library.DTO.SubjectDTO;
import com.example.uetstudy.library.Model.Document;
import com.example.uetstudy.library.Service.DocumentService;
import com.example.uetstudy.library.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class DocumentController {

    @Autowired
    private DocumentService documentService;
    @Autowired
    private SubjectService subjectService;
    @GetMapping("/allDocument")
    @ResponseBody
    Page<Document> findAllDocument(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(10);
        return documentService.findDocumentsByStatus(PageRequest.of(currentPage, pageSize));
    }
    @GetMapping("/allSubject")
    @ResponseBody
    private List<SubjectDTO> allSubject(){
        return subjectService.findAllSubjectDTO();
    }
    @GetMapping("/delete-document")
    public String deleteDocument(Long id){
        documentService.deleteDocumentById(id);
        return "redirect:/admin/document";
    }
    @GetMapping("/approve-document")
    public String approveDocumentById(Long id){
        documentService.approveDocumentById(id);
        return "redirect:/admin/document";
    }
    @PatchMapping("update-document/documentId={id}")
    public Document update(@PathVariable Long id,@RequestParam("pdf_file") MultipartFile file, Document document){
        return documentService.update(id,file,document);
    }

}
