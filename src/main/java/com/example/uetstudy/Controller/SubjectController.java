package com.example.uetstudy.Controller;

import com.example.uetstudy.library.Model.Subject;
import com.example.uetstudy.library.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Controller
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @GetMapping("/getSubjectToUpdate")
    @ResponseBody
    public Optional<Subject> getSubject(Long id){
        return subjectService.getSubjectById(id);
    }
    @PostMapping("/update-subject")
    public String updateSubject(Subject subject){
        subjectService.update(subject);
        return "redirect:/admin/subject";
    }
    @PostMapping("/addSubject")
    public String save(Subject subject){
        subjectService.save(subject);
        return "redirect:/admin/subject";
    }
    @GetMapping("/delete-subject")
    public String deleteSubject(Long id, RedirectAttributes redirectAttributes) {
        try {
            subjectService.deleteSubjectById(id);
            return "redirect:/admin/subject";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/subject?error=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);

        }
    }


}
