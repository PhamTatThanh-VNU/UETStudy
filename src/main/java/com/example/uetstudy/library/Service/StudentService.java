package com.example.uetstudy.library.Service;

import com.example.uetstudy.library.DTO.StudentDTO;
import com.example.uetstudy.library.Model.Student;

public interface StudentService {
    Student save(StudentDTO studentDTO);
    Student findByUserName(String userName);
    public void updateResetPasswordToken(String token, String email) throws Exception;
    public Student getByResetPasswordToken(String token);
    public void updatePassword(Student student, String newPassword);
}
