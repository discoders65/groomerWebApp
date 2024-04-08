package com.wjadczak.groomerWebApp;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEncryptableProperties
@EnableTransactionManagement
@EnableDiscoveryClient
public class GroomerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroomerWebApplication.class, args);
	}

}
