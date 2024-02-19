package com.wjadczak.groomerWebApp;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@EnableEncryptableProperties
public class GroomerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroomerWebApplication.class, args);
	}

}
