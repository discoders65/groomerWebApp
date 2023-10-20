package com.wjadczak.groomerWebApp.service;

import com.wjadczak.groomerWebApp.controller.dto.SignUpDto;
import com.wjadczak.groomerWebApp.entity.Role;
import com.wjadczak.groomerWebApp.entity.User;
import com.wjadczak.groomerWebApp.repository.RoleRepository;
import com.wjadczak.groomerWebApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor //TODO ReqARgs
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;

    public void saveUser(SignUpDto signUpDto){ // TODO saveUser(SignUpDto signUpDto) ==> saveUser(UserDto user)

        // TODO UserDto map to User
        User user = new User();
        user.setName(signUpDto.getName()); //TODO MapStruct
        user.setUserName(signUpDto.getUserName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());
        Role role = roleRepository.findByName("ROLE_ADMIN"); //TODO to delete
        if(role == null){
            role = createAdmin();
        }
        user.setRoles(Set.of(role));

        User saved = userRepository.save(user); // TODO return created user
        // TODO saved map to UserDto
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


    public Role createAdmin(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }


}
