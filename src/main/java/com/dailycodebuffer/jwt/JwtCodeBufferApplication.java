package com.dailycodebuffer.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtCodeBufferApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtCodeBufferApplication.class, args);
	}
	
	
	// STEP 8 : We are using No Password Encoder
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
		

}
