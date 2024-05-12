package com.example.uetstudy.library.DTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    @Size(min = 6, max = 30)
    private String fullName;
    @Size(min = 6, max = 30, message = "Last name contains 6-30 characters")
    private String userName;
    @Size(min = 6, message = "Password contains least 6 characters")
    private String password;
    private String repeatPassword;
    private String providerId;
    private String role;
}
