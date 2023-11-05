package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.controller.dto.SignUpDto;
import com.wjadczak.groomerWebApp.controller.dto.UserDto;
import com.wjadczak.groomerWebApp.controller.mapper.SignUpDtoToUserMapper;
import com.wjadczak.groomerWebApp.controller.mapper.UserToUserDtoMapper;
import com.wjadczak.groomerWebApp.entity.Role;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import com.wjadczak.groomerWebApp.repository.RoleRepository;
import com.wjadczak.groomerWebApp.repository.UserRepository;
import com.wjadczak.groomerWebApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;

    public UserDto createNewUser(SignUpDto signUpDto){

        UserEntity user = new UserEntity();
        Role role = roleRepository.findByName("USER");
        if(role == null){
            role = new Role();
            role.setName("USER");
        }

        user.setRole(role);
        user = SignUpDtoToUserMapper.signUpDtoToUserMapper.signUpDtoToUser(signUpDto);


        UserEntity saved = userRepository.save(user);
        UserDto savedUserDto = UserToUserDtoMapper.userToUserDtoMapper.userToUserDto(saved);
        return savedUserDto;
    }

    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public UserEntity findUserByUserName(String userName){
        return userRepository.findByUserName(userName);
    }





}
