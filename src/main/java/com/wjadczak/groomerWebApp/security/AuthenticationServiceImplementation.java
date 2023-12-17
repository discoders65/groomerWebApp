package com.wjadczak.groomerWebApp.security;


import com.wjadczak.groomerWebApp.dao.request.SignInRequest;
import com.wjadczak.groomerWebApp.dao.request.SignUpRequest;
import com.wjadczak.groomerWebApp.dao.response.JwtAuthenticationResponse;
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
    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {
        validateSignUpRequest(signUpRequest);
        UserEntity user = UserEntity.builder().name(signUpRequest.getName()).userName(signUpRequest.getUser_name())
                .email(signUpRequest.getEmail()).password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.USER).mobile(signUpRequest.getMobile()).build();
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
        UserEntity user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new InvalidUserDataInputException("Invalid email or password."));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    private void validateSignUpRequest(SignUpRequest signUpRequest) {
        if (nonNull(signUpRequest)) {
            boolean anySignUpInputIsNull = Stream.of(signUpRequest.getEmail(),
                            signUpRequest.getPassword(),
                            signUpRequest.getUser_name(),
                            signUpRequest.getName(),
                            signUpRequest.getMobile())
                    .anyMatch(Objects::isNull);
            if (anySignUpInputIsNull) {
                throw new InvalidUserDataInputException("All fields in the user registration must be filled out.");
            } else if (userRepository.existsByEmail(signUpRequest.getEmail()) || userRepository.existsByUserName(signUpRequest.getUser_name())) {
                throw new UserAlreadyExistsException("User registered with this mail and/or username already exists");
            } else {
                throw new InvalidUserDataInputException("User registration form can not be empty");
            }
        }
    }
}
