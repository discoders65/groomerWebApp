package com.wjadczak.groomerWebApp.config.security;

import com.wjadczak.groomerWebApp.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
// dopisać testy, sprawdzic co zwraca i w jakich sytuacjach
// zgooglaj programowanie aspektowe
@Component
public class AuthenticationHelper {

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public UserEntity getCurrentUser(){
        return (UserEntity) getAuthentication().getPrincipal();
    }

}
