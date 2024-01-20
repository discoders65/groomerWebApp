package com.wjadczak.groomerWebApp;

import com.wjadczak.groomerWebApp.entity.AppointmentEntity;
import com.wjadczak.groomerWebApp.entity.UserEntity;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestUtils {

    public static final String VALID_APPOINTMENT_START_DATE = "2024-01-14 12:00:00";
    public static final String VALID_APPOINTMENT_END_DATE = "2024-01-14 14:00:00";
    public static final String INVALID_APPOINTMENT_DATE = "1024-01-01 01:01:01";
    public static final LocalDateTime VALID_START_DATE_TIME = LocalDateTime.of(2024, 1, 14, 12, 0,0);
    public static final LocalDateTime VALID_END_DATE_TIME = LocalDateTime.of(2024, 1, 14, 14, 0,0);
    public static final UUID NON_EXSITENT_USER_ID = UUID.fromString("548ac69c-a182-4a5d-9720-154e356a3d3f");
    public static final List<AppointmentEntity> VALID_APPOINTMENT = Arrays.asList(
            new AppointmentEntity(
                    UUID.randomUUID(),
                    VALID_START_DATE_TIME,
                    VALID_END_DATE_TIME,
                    "comment",
                    Mockito.mock(UserEntity.class),
                    new BigDecimal("100"),
                    false));
    public static final  String NON_EXITENT_EMAIL = "nonexistent@nonexistent.com";

}
