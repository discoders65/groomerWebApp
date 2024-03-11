package com.wjadczak.groomerWebApp.integration;

import com.wjadczak.groomerWebApp.TestUtils;
import com.wjadczak.groomerWebApp.configuration.IntegrationTestConfig;
import com.wjadczak.groomerWebApp.dto.SignInDto;
import com.wjadczak.groomerWebApp.dto.SignUpDto;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import com.wjadczak.groomerWebApp.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthenticationControllerTest extends IntegrationTestConfig {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.save(TestUtils.TEST_USER);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void shouldSaveUserIfGivenValidDto() throws Exception {
        SignUpDto signUpDto = SignUpDto.builder()
                .email("email")
                .mobile(999999999)
                .name("name")
                .userName("UserName")
                .password("Password!")
                .build();

        mockMvc.perform(
                post("/api/v1/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpDto)))
                .andExpect(status().isOk());
        List<UserEntity> allUsers = userRepository.findAll();
        Assertions.assertEquals(2, allUsers.size());
    }

    @Test
    void shouldThrowUserAlreadyExistsExceptionIfUserExists() throws Exception {
        SignUpDto signUpDto = SignUpDto.builder()
                .email("email")
                .mobile(999999999)
                .name("name")
                .userName("userName")
                .password("Password!")
                .build();

        mockMvc.perform(
                        post("/api/v1/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(signUpDto)))
                .andExpect(status().isBadRequest());
        List<UserEntity> allUsers = userRepository.findAll();
        Assertions.assertEquals(1, allUsers.size());
    }

    @Test
    void shouldReturnStatusOkIfSignInSucceed() throws Exception {
        SignInDto signInDto = SignInDto.builder()
                .email(TestUtils.TEST_USER.getEmail())
                .password("Password!")
                .build();

        mockMvc.perform(
                post("/api/v1/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signInDto)))
                .andExpect(status().isOk());

    }
}
