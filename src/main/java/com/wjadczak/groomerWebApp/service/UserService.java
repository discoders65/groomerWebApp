package com.wjadczak.groomerWebApp.service;

import com.wjadczak.groomerWebApp.dto.UserDto;
import com.wjadczak.groomerWebApp.entity.Role;
import com.wjadczak.groomerWebApp.entity.User;
import com.wjadczak.groomerWebApp.repository.RoleRepository;
import com.wjadczak.groomerWebApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;

    public void saveUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(Set.of(role));

        userRepository.save(user);
    }
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public User findUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }


}
