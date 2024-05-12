package com.example.uetstudy.Controller;
import com.example.uetstudy.library.Model.Department;
import com.example.uetstudy.library.Model.Document;
import com.example.uetstudy.library.Model.KnowledgeBlock;
import com.example.uetstudy.library.Model.Subject;
import com.example.uetstudy.library.Service.DepartmentService;
import com.example.uetstudy.library.Service.DocumentService;
import com.example.uetstudy.library.Service.KnowledgeBlockService;
import com.example.uetstudy.library.Service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController{
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private KnowledgeBlockService knowledgeBlockService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private DocumentService documentService;
    @GetMapping("/admin")
    public String adminView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Department> departments = departmentService.findAll();
        model.addAttribute("authentication",authentication);
        model.addAttribute("allDepartments",departments);
        model.addAttribute("department", new Department());
        return "admin-department";
    }
    @GetMapping("/admin/knowledge")
    public String knowlegeView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Department> departments = departmentService.findAll();
        List<KnowledgeBlock> knowledgeBlocks = knowledgeBlockService.findAll();
        model.addAttribute("allDepartments",departments);
        model.addAttribute("authentication",authentication);
        model.addAttribute("knowledgeBlocks",knowledgeBlocks);
        model.addAttribute("knowledge", new KnowledgeBlock());
        return "admin-knowledge";
    }
    @GetMapping("/admin/subject")
    public String subjectView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Subject> subjects = subjectService.findAllSubject();
        List<KnowledgeBlock> knowledgeBlocks = knowledgeBlockService.findAll();
        model.addAttribute("authentication",authentication);
        model.addAttribute("allSubject",subjects);
        model.addAttribute("knowledgeBlocks",knowledgeBlocks);
        model.addAttribute("subject", new Subject());
        return "admin-subject";
    }
    @GetMapping("/admin/document")
    public String documentView(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(10);
        Page<Document> documentPageTrue = documentService.findDocumentsByStatus(PageRequest.of(currentPage, pageSize));
        Page<Document> documentPageFalse = documentService.findDocumentsByStatusFalse(PageRequest.of(currentPage, pageSize));
        model.addAttribute("authentication",authentication);
        model.addAttribute("allDocumentsFalse", documentPageFalse);
        model.addAttribute("allDocumentsTrue", documentPageTrue);
        return "admin-document";
    }

}
