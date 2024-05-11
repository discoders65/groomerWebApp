package com.wjadczak.groomerWebApp.config;

import com.wjadczak.groomerWebApp.dto.SignInDto;
import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import com.wjadczak.groomerWebApp.entity.Role;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class TestUtils {
    @Value("${spring.rabbitmq.registrationRoutingKey}")
    public static String REGISTRATION_ROUTING_KEY;
    @Value("${spring.rabbitmq.confirmationRoutingKey}")
    public static String CONFIRMATION_ROUTING_KEY;
    @Value("${spring.rabbitmq.cancellationRoutingKey}")
    public static String CANCELLATION_ROUTING_KEY;
    @Value("${spring.rabbitmq.exchangeName}")
    public static String EXCHANGE_NAME;
    public static final LocalDateTime FIXED_DATE_TIME = LocalDateTime.of(2024, 4, 21, 10, 0, 0);
    public static final String VALID_APPOINTMENT_START_DATE = "2024-01-14 12:00:00";
    public static final String TEST_APPOINTMENT_START_DATE = "2024-02-14 12:00:00";
    public static final String VALID_APPOINTMENT_END_DATE = "2024-01-14 14:00:00";
    public static final String INVALID_APPOINTMENT_DATE = "1024-01-01 01:01:01";
    public static final LocalDateTime TEST_START_DATE_TIME = LocalDateTime.of(2024, 2, 14, 12, 0,0);
    public static final LocalDateTime VALID_START_DATE_TIME = LocalDateTime.of(2024, 1, 14, 12, 0,0);
    public static final LocalDateTime VALID_END_DATE_TIME = LocalDateTime.of(2024, 1, 14, 14, 0,0);
    public static final UUID NON_EXSITENT_USER_ID = UUID.fromString("548ac69c-a182-4a5d-9720-154e356a3d3f");
    public static final UUID NON_EXSITENT_IMAGE_ID = UUID.fromString("c5dc3393-4a3f-41bf-b81d-4cc1b4dd71fc");
    public static final UserEntity TEST_USER;
    public static final String TEST_USER_PASSWORD ="Password!";
    static {
        TEST_USER = createUserEntity();
    }
    public static final List<AppointmentEntity> TEST_APPOINTMENTS = Arrays.asList(
            AppointmentEntity.builder()
                    .id(UUID.randomUUID())
                    .dateStart(VALID_START_DATE_TIME)
                    .dateEnd(VALID_END_DATE_TIME)
                    .comment("comment")
                    .userEntity(Mockito.mock(UserEntity.class))
                    .pricing(new BigDecimal("100"))
                    .accepted(false)
                    .cancelled(false)
                    .utilities(new HashSet<>()) // Add utilities if needed
                    .employee(Mockito.mock(UserEntity.class))
                    .build()
    );

    public static final AppointmentEntity TEST_APPOINTMENT = AppointmentEntity
            .builder()
            .id(UUID.randomUUID())
            .dateStart(VALID_START_DATE_TIME)
            .dateEnd(VALID_END_DATE_TIME)
            .comment("comment")
            .userEntity(TEST_USER)
            .pricing(new BigDecimal("100"))
            .accepted(false)
            .cancelled(false)
            .build();

    private static final UserEntity createUserEntity(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(TEST_USER_PASSWORD);
        return UserEntity.builder()
                .userName("userName")
                .email("email@email.com")
                .mobile(123456789)
                .name("user")
                .password(hashedPassword)
                .role(Role.USER)
                .build();
    }
    public static final SignInDto SIGN_IN_DTO = SignInDto.builder()
            .email("email@email.com")
            .password("Password!")
            .build();
    public static final  String NON_EXITENT_EMAIL = "nonexistent@nonexistent.com";
    public static final UUID NON_EXISTENT_APPOINTMENT_ID = UUID.fromString("b49c15fa-3e57-41e5-a640-c752956314e3");

}
