package com.example.uetstudy.Oauth2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class OAuth2UserDetailCustom implements OAuth2User {
    private OAuth2User oAuth2User;
    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oAuth2User.getAttribute("email");
    }
    public String getEmail(){
        return oAuth2User.getAttribute("name");
    }
}
