package com.wjadczak.groomerWebApp.service;


import com.wjadczak.groomerWebApp.controller.dto.SignUpDto;
import com.wjadczak.groomerWebApp.controller.dto.UserDto;
import com.wjadczak.groomerWebApp.controller.mapper.SignUpDtoToUserMapper;
import com.wjadczak.groomerWebApp.controller.mapper.UserToUserDtoMapper;
import com.wjadczak.groomerWebApp.entity.Role;
import com.wjadczak.groomerWebApp.entity.User;
import com.wjadczak.groomerWebApp.repository.RoleRepository;
import com.wjadczak.groomerWebApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserDto createNewUser(SignUpDto signUpDto){

        User user = new User();
        Role role = roleRepository.findByName("USER");
        if(role == null){
//            role = new Role();
//            role.setName("USER");
        }

//        user.setRole(role);
        user = SignUpDtoToUserMapper.signUpDtoToUserMapper.signUpDtoToUser(signUpDto);

        User saved = userRepository.save(user);
        UserDto savedUserDto = UserToUserDtoMapper.userToUserDtoMapper.userToUserDto(saved);
        return savedUserDto;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public User findUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }





}
