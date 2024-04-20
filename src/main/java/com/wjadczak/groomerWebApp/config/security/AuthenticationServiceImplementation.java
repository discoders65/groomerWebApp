package com.wjadczak.groomerWebApp.config.security;


import com.wjadczak.groomerWebApp.config.validation.PasswordValidatorProcessor;
import com.wjadczak.groomerWebApp.dto.JwtAuthenticationResponseDto;
import com.wjadczak.groomerWebApp.dto.SignInDto;
import com.wjadczak.groomerWebApp.dto.SignUpDto;
import com.wjadczak.groomerWebApp.entity.Role;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import com.wjadczak.groomerWebApp.errors.ErrorMessages;
import com.wjadczak.groomerWebApp.errors.InvalidUserDataInputException;
import com.wjadczak.groomerWebApp.errors.UserAlreadyExistsException;
import com.wjadczak.groomerWebApp.repository.UserRepository;
import com.wjadczak.groomerWebApp.service.NotificationService;
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
    private final PasswordValidatorProcessor passwordValidatorProcessor;
    private final NotificationService notificationService;
    @Override
    public void signUp(SignUpDto signUpDto) {
        validateSignUpRequest(signUpDto);
        validatePassword(signUpDto);
        UserEntity user = UserEntity.builder().name(signUpDto.getName()).userName(signUpDto.getUserName())
                .email(signUpDto.getEmail()).password(passwordEncoder.encode(signUpDto.getPassword()))
                .role(Role.USER).mobile(signUpDto.getMobile()).build();
        userRepository.save(user);
        notificationService.sendRegistrationNotification(signUpDto.getEmail());
    }

    @Override
    public JwtAuthenticationResponseDto signIn(SignInDto signInDto) {
        UserEntity user = userRepository.findByEmail(signInDto.getEmail())
                .orElseThrow(() -> new InvalidUserDataInputException(ErrorMessages.INVALID_USER_OR_EMAIL));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDto.getEmail(), signInDto.getPassword()));
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
            boolean emailOrUsernameExists = Stream.of(
                    userRepository.existsByEmail(signUpDto.getEmail()),
                            userRepository.existsByUserName(signUpDto.getUserName())
            ).anyMatch(Boolean.TRUE::equals);
            if (anySignUpInputIsNull) {
                throw new InvalidUserDataInputException(ErrorMessages.REGISTRATION_FIELDS_MUST_BE_FILLED_OUT);
            } else if (emailOrUsernameExists) {
                throw new UserAlreadyExistsException(ErrorMessages.USER_ALREADY_EXISTS);
            }
        }else {
            throw new InvalidUserDataInputException(ErrorMessages.EMPTY_REGISTRATION_FORM);
        }
    }

    private void validatePassword(SignUpDto signUpDto){
        passwordValidatorProcessor.validate(signUpDto.getPassword());
    }

}
