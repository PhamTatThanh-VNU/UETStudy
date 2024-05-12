package com.example.uetstudy.library.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student",uniqueConstraints = @UniqueConstraint(columnNames = "student_id"))
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "fullname", length = 100)
    private String fullName;
    @Column(name ="role")
    private String role;

    @Column(name = "provider_id")
    private String provider_id;
    @Column(name = "reset_password_token")
    private String resetPasswordToken;
}
