package com.example.uetstudy.config_security;


import com.example.uetstudy.library.Model.Student;
import com.example.uetstudy.library.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentDetailsConfig implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username);

        if (student == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new StudentDetail(student);
    }
}
