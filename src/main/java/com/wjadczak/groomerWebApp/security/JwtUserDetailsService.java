package com.wjadczak.groomerWebApp.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        if("groomer".equals(username)){
            return new User("groomer", "$2a$10$0gTqdkBrnPK065XPEoungudZbgOAYGfPZIpusfD9fAabJvRLMhqG2", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User with username: " + username + " not found.");
        }
    }
}
