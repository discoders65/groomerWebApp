package com.wjadczak.groomerWebApp.config.security;


import com.wjadczak.groomerWebApp.dto.SignUpDto;
import com.wjadczak.groomerWebApp.dto.SignInDto;
import com.wjadczak.groomerWebApp.dto.JwtAuthenticationResponseDto;
import com.wjadczak.groomerWebApp.entity.Role;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import com.wjadczak.groomerWebApp.errors.InvalidUserDataInputException;
import com.wjadczak.groomerWebApp.errors.UserAlreadyExistsException;
import com.wjadczak.groomerWebApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplementation implements AuthenticationService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public void signUp(SignUpDto signUpDto) {
        validateSignUpRequest(signUpDto);
        UserEntity user = UserEntity.builder().name(signUpDto.getName()).userName(signUpDto.getUserName())
                .email(signUpDto.getEmail()).password(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Role.USER).mobile(signUpDto.getMobile()).build();
        userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponseDto signIn(SignInDto signInDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword()));
        UserEntity user = userRepository.findByEmail(signInDto.getEmail())
                .orElseThrow(() -> new InvalidUserDataInputException("Invalid email or password."));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponseDto.builder().token(jwt).build();
    }

    private void validateSignUpRequest(SignUpDto signUpDto) {
        if (nonNull(signUpDto)) {
            boolean anySignUpInputIsNull = Stream.of(
                    signUpDto.getEmail(),
                            signUpDto.getPassword(),
                            signUpDto.getUserName(),
                            signUpDto.getName(),
                            signUpDto.getMobile())
                    .anyMatch(Objects::isNull);
            if (anySignUpInputIsNull) {
                throw new InvalidUserDataInputException("All fields in the user registration must be filled out.");
            } else if (userRepository.existsByEmail(signUpDto.getEmail()) || userRepository.existsByUserName(signUpDto.getUserName())) {
                throw new UserAlreadyExistsException("User registered with this mail and/or username already exists");
            }
        }else {
            throw new InvalidUserDataInputException("User registration form can not be empty");
        }
    }
}
