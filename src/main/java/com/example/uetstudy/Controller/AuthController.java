package com.example.uetstudy.Controller;

import com.example.uetstudy.library.DTO.StudentDTO;
import com.example.uetstudy.library.Model.Student;
import com.example.uetstudy.library.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final StudentService studentService;
    private final BCryptPasswordEncoder passwordEncoder;
    @RequestMapping("/login")
    public String login(Model model){
        return "login";
    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("student",new StudentDTO());
        return "register";
    }
    @PostMapping("/register-new")
    public String register(@Valid @ModelAttribute("student") StudentDTO studentDto, BindingResult result, Model model){
        try {
            if (result.hasErrors()) {
                model.addAttribute("student", studentDto);
                return "register";
            }
            String username = studentDto.getUserName();
            Student student = studentService.findByUserName(username);

            if (student != null) {
                model.addAttribute("student", studentDto);
                model.addAttribute("emailError", "Your username has been registered!");
                return "register";
            }

            if (studentDto.getPassword().equals(studentDto.getRepeatPassword())) {
                studentDto.setPassword(passwordEncoder.encode(studentDto.getPassword()));
                studentDto.setProviderId("local");
                studentDto.setRole("USER");
                studentService.save(studentDto);
                model.addAttribute("success", "Register successfully!");
                model.addAttribute("studentDto", studentDto);
            } else {
                model.addAttribute("studentDto", studentDto);
                model.addAttribute("passwordError", "Your password maybe wrong! Check again!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errors", "The server has been wrong!");
        }
        return "register";
    }

}
