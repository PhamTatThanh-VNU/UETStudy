package com.example.uetstudy.library.Service.ServiceImpl;

import com.example.uetstudy.library.DTO.StudentDTO;
import com.example.uetstudy.library.Model.Student;
import com.example.uetstudy.library.Repository.StudentRepository;
import com.example.uetstudy.library.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    @Override
    public Student save(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFullName(studentDTO.getFullName());
        student.setUsername(studentDTO.getUserName());
        student.setPassword(studentDTO.getPassword());
        student.setRole(studentDTO.getRole());
        return studentRepository.save(student);
    }
    @Override
    public Student findByUserName(String userName) {
        return studentRepository.findByUsername(userName);
    }
    @Override
    public Student getByResetPasswordToken(String token) {
        return studentRepository.findByResetPasswordToken(token);
    }
    @Override
    public void updatePassword(Student student, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        student.setPassword(encodedPassword);

        student.setResetPasswordToken(null);
        studentRepository.save(student);
    }
    public void updateResetPasswordToken(String token, String email) throws Exception {
        Student student = studentRepository.findByUsername(email);
        if (student != null) {
            student.setResetPasswordToken(token);
            studentRepository.save(student);
        } else {
            throw new Exception("Could not find any customer with the email " + email);
        }
    }
}
