package com.example.uetstudy.Controller;

import com.example.uetstudy.library.Model.KnowledgeBlock;
import com.example.uetstudy.library.Service.KnowledgeBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Controller
public class KnowledgeController {
    @Autowired
    private KnowledgeBlockService knowledgeBlockService;
    @GetMapping("/knowledgeBlock")
    public Optional<KnowledgeBlock> getKnowledgeBlock(Long id) {
        return knowledgeBlockService.findById(id);
    }
    @GetMapping("/getKnowledgeBlock")
    @ResponseBody
    public Optional<KnowledgeBlock> getKnowledgeBlockById(Long id) {
        return knowledgeBlockService.findById(id);
    }

    @PostMapping("/addKnowledgeBlock")
    public String save(KnowledgeBlock knowledgeBlock){
        knowledgeBlockService.save(knowledgeBlock);
        return "redirect:/admin/knowledge";
    }
    @PostMapping("/update-knowledgeBlock")
    public String update(KnowledgeBlock knowledgeBlock){
        knowledgeBlockService.update(knowledgeBlock);
        return "redirect:/admin/knowledge";
    }

    @GetMapping("/delete-knowledgeBlock")
    public String deleteDepartment(Long id , RedirectAttributes redirectAttributes){
        try {
            knowledgeBlockService.deleteKnowledgeBlockById(id);
            return "redirect:/admin/knowledge";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/knowledge?error=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }
    }
}
