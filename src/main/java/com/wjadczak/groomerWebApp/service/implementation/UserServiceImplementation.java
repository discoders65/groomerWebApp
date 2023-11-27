package com.wjadczak.groomerWebApp.service.implementation;

import com.wjadczak.groomerWebApp.repository.UserRepository;
import com.wjadczak.groomerWebApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    //    public UserDto createNewUser(SignUpDto signUpDto){
//
//        UserEntity user = new UserEntity();
//        Role role = roleRepository.findByName("USER");
//        if(role == null){
//            role = new Role();
//            role.setName("USER");
//        }
//
//        user.setRole(role);
//        user = SignUpDtoToUserMapper.signUpDtoToUserMapper.signUpDtoToUser(signUpDto);
//
//
//        UserEntity saved = userRepository.save(user);
//        UserDto savedUserDto = UserToUserDtoMapper.userToUserDtoMapper.userToUserDto(saved);
//        return savedUserDto;
//    }
//
//    public List<UserEntity> getAllUsers(){
//        return userRepository.findAll();
//    }
//
//    public UserEntity findUserByEmail(String email){
//        return userRepository.findByEmail(email);
//    }
//    public UserEntity findUserByUserName(String userName){
//        return userRepository.findByUserName(userName);
//    }





}
