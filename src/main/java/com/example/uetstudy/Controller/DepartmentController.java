package com.example.uetstudy.Controller;

import com.example.uetstudy.library.Model.Department;
import com.example.uetstudy.library.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    //Department-Controller
    @PostMapping("/addDepartment")
    public String save(Department department){
        departmentService.save(department);
        return "redirect:/admin";
    }
    @GetMapping( "/findDepartmentById")
    @ResponseBody
    public Optional<Department> getDepartmentById(Long id) {
        return departmentService.findById(id);
    }

    @PostMapping("/update-department")
    public String update(Department department){
        departmentService.update(department);
        return "redirect:/admin";
    }
    @GetMapping("/delete-department")
    public String deleteDepartment(Long id, RedirectAttributes redirectAttributes){
        try {
            departmentService.deleteDepartmentById(id);
            return "redirect:/admin";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin?error=" + URLEncoder.encode(e.getMessage(), StandardCharsets.UTF_8);
        }

    }
}
