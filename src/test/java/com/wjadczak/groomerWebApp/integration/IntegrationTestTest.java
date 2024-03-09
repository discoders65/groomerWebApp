package com.wjadczak.groomerWebApp.integration;

import com.wjadczak.groomerWebApp.TestUtils;
import com.wjadczak.groomerWebApp.configuration.IntegrationTestConfig;
import com.wjadczak.groomerWebApp.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IntegrationTestTest extends IntegrationTestConfig {

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
    void name() {
        System.out.println(userRepository.findAll());
    }
}
