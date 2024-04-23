package com.wjadczak.groomerWebApp.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wjadczak.groomerWebApp.config.security.AuthenticationHelper;
import com.wjadczak.groomerWebApp.config.IntegrationTestConfig;
import com.wjadczak.groomerWebApp.config.TestUtils;
import com.wjadczak.groomerWebApp.dto.AppointmentDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSaveRequestDto;
import com.wjadczak.groomerWebApp.dto.AppointmentSearchRequestDto;
import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import com.wjadczak.groomerWebApp.repository.AppointmentRepository;
import com.wjadczak.groomerWebApp.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AppointmentControllerTest extends IntegrationTestConfig {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AuthenticationHelper authenticationHelper;
    @Autowired
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        userRepository.save(TestUtils.TEST_USER);
        appointmentRepository.save(TestUtils.TEST_APPOINTMENT);
        authenticateTestUser();
    }

    @AfterEach
    void tearDown() {
        appointmentRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void shouldSaveAppointmentIfValidSaveRequestDtoProvided() throws Exception {
        AppointmentSaveRequestDto appointmentSaveRequestDto = AppointmentSaveRequestDto.builder()
                .startDateTime(TestUtils.TEST_APPOINTMENT_START_DATE)
                .build();
        mockMvc.perform(
                post("/api/v1/calendar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appointmentSaveRequestDto)))
                .andExpect(status().isCreated());
        List<AppointmentEntity> appointments = appointmentRepository.findByDateStartBetween(TestUtils.TEST_START_DATE_TIME, TestUtils.TEST_START_DATE_TIME);
        Assertions.assertEquals(1, appointments.size());
    }

    @Test
    void shouldReturnTestAppointmentIfValidSearchRequestDtoProvided() throws Exception {
        AppointmentSearchRequestDto appointmentSearchRequestDto = AppointmentSearchRequestDto.builder()
                .startDateTime(TestUtils.VALID_APPOINTMENT_START_DATE)
                .endDateTime(TestUtils.VALID_APPOINTMENT_START_DATE)
                .build();
        String responseContent = mockMvc.perform(
                get("/api/v1/calendar")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appointmentSearchRequestDto)))
                .andExpect(
                        status()
                                .isOk())
                .andExpect(
                        content()
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        List<AppointmentDto> foundAppointmentList = objectMapper.readValue(responseContent, new TypeReference<List<AppointmentDto>>() {
        });
        Assertions.assertEquals(foundAppointmentList.size(), 1);
    }

    private void authenticateTestUser(){
        UserDetails userDetails = userRepository.findByEmail(TestUtils.TEST_USER.getEmail()).orElseThrow();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, TestUtils.TEST_USER_PASSWORD, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authenticationToken));
    }
}
