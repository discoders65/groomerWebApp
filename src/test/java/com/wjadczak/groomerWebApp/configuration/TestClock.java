package com.wjadczak.groomerWebApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.ZoneId;
@Configuration
public class TestClock {
    @Bean
    @Primary
    public static Clock getFixedClock(){
        return Clock.fixed(TestUtils.FIXED_DATE_TIME.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    }
}
