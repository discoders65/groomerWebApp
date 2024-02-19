package com.wjadczak.groomerWebApp.config.encryption;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Bean(name = "jasyptStringEncryptor")
    public StringEncryptor encryptor() {
        return new CustomStringEncryptor();
    }
}
