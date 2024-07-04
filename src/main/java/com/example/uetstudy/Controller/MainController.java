package com.example.uetstudy.Controller;

import com.example.uetstudy.library.DTO.DocumentDTO;
import com.example.uetstudy.library.DTO.NoteDTO;
import com.example.uetstudy.library.DTO.PostDTO;
import com.example.uetstudy.library.Model.*;
import com.example.uetstudy.library.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private PostService postService;

    @GetMapping("/course")
    public String courseView(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Check Authentication
        if (authentication != null) {
            Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
            List<String> roleNames = roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            model.addAttribute("roles", roleNames);
        }
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(10);
        List<Department> departments = departmentService.findAll();
        Page<Document> documentPage = documentService.findDocumentsByStatus(PageRequest.of(currentPage, pageSize));
        model.addAttribute("allDocuments", documentPage);
        model.addAttribute("allDepartment", departments);
        model.addAttribute("authentication",authentication);
        return "course";
    }

    @PostMapping("/share")
    public String save(MultipartFile file,Document document) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            Student student = studentService.findByUserName(authentication.getName());
            document.setStudent(student);
            documentService.save(file, document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/share";
    }

    @GetMapping("/share")
    public String shareView(Model model,RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        List<String> roleNames = roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        //model
        model.addAttribute("roles", roleNames);
        model.addAttribute("authentication",authentication);
        model.addAttribute("document", new Document());
        model.addAttribute("roles",roleNames);
        return "share";
    }
    @GetMapping("/your-documents")
    private String documentUserView(Model model){
        //Get Information of authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        List<String> roleNames = roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        //All document of one student
        Student student = studentService.findByUserName(authentication.getName());
        List<DocumentDTO> allDocumentOfStudent = documentService.findDocumentByStudentId(student.getStudentId());;
        //model
        model.addAttribute("student",student);
        model.addAttribute("allDocumentOfStudent",allDocumentOfStudent);
        model.addAttribute("roles", roleNames);
        model.addAttribute("authentication",authentication);
        return "your-document";
    }



    @GetMapping("/note")
    public String noteView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        List<String> roleNames = roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        //All note of one student
        Student student = studentService.findByUserName(authentication.getName());
        List<NoteDTO> allNoteOfStudent = noteService.findNoteByStudentId(student.getStudentId());;

        model.addAttribute("roles", roleNames);
        model.addAttribute("authentication",authentication);
        model.addAttribute("note", new Note());
        model.addAttribute("student",student);
        model.addAttribute("allNoteOfStudent",allNoteOfStudent);
        return "note";
    }

    @PostMapping("/saveNote")
    public String save(Note note,Model  model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        try {
            Student student = studentService.findByUserName(authentication.getName());
            note.setStudent(student);
            noteService.save(note);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/note";
    }

    @GetMapping("/blog-single")
    public String blogView(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        List <Post> posts= postService.findAll();
        model.addAttribute("post",new Post());
        model.addAttribute("allPost",posts);
        model.addAttribute("userName", authentication.getName());
        model.addAttribute("roles",roles);
        model.addAttribute("authentication",authentication);
        return "blog-single";
    }
}
