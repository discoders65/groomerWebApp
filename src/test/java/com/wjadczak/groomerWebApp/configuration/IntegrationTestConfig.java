package com.wjadczak.groomerWebApp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("integration-testing")
public class IntegrationTestConfig {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    protected static PostgreSQLContainer POSTGRESQL_CONTAINER = new PostgreSQLContainer<>("postgres:14");
    @DynamicPropertySource
    protected static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.datasource.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", POSTGRESQL_CONTAINER::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", POSTGRESQL_CONTAINER::getPassword);
        dynamicPropertyRegistry.add("flyway.user", POSTGRESQL_CONTAINER::getUsername);
        dynamicPropertyRegistry.add("flyway.password", POSTGRESQL_CONTAINER::getPassword);
        dynamicPropertyRegistry.add("flyway.url", POSTGRESQL_CONTAINER::getJdbcUrl);

    }
    static {
        POSTGRESQL_CONTAINER.withUsername("groomer");
        POSTGRESQL_CONTAINER.start();
    }
}
