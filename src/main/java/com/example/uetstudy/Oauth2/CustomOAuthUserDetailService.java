package com.example.uetstudy.Oauth2;


import com.example.uetstudy.library.DTO.StudentDTO;
import com.example.uetstudy.library.Model.Student;
import com.example.uetstudy.library.Repository.StudentRepository;
import com.example.uetstudy.library.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomOAuthUserDetailService extends DefaultOAuth2UserService{
    @Autowired
    private StudentService studentService;
    @Autowired
    private final StudentRepository studentRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return processOAuth2User(userRequest,oAuth2User);
    }
    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setUserName(oAuth2User.getAttributes().get("email").toString());
        studentDTO.setFullName(oAuth2User.getAttributes().get("name").toString());
        studentDTO.setProviderId(oAuth2UserRequest.getClientRegistration().getClientName());
        studentDTO.setRole("USER");

        Student student = studentService.findByUserName(studentDTO.getUserName());
        if(student == null){
            registerNewUser(oAuth2UserRequest,studentDTO);
        }
        else{
            updateExistingUser(student,studentDTO);
        }
        return new OAuth2UserDetailCustom(oAuth2User);
    }

    private Student registerNewUser(OAuth2UserRequest oAuth2UserRequest, StudentDTO studentInfoDTO) {
        Student student = new Student();
        student.setProvider_id(oAuth2UserRequest.getClientRegistration().getRegistrationId());
        student.setFullName(studentInfoDTO.getFullName());
        student.setUsername(studentInfoDTO.getUserName());
        student.setRole(studentInfoDTO.getRole());
        return studentRepository.save(student);
    }

    private Student updateExistingUser(Student existingStudent, StudentDTO studentInfoDTO) {
        existingStudent.setFullName(studentInfoDTO.getFullName());
        existingStudent.setProvider_id(studentInfoDTO.getProviderId());
        return studentRepository.save(existingStudent);
    }

}
