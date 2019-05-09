package com.synchrony.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: SychronyApplication is and entry point to start RESTful web service 
 * using spring boot. 
 * All require end points are implemented in RestController. 
 * @author Aviprash
 *
 */
@SpringBootApplication
public class SychronyApplication {
	public static void main(String[] args) {
		SpringApplication.run(SychronyApplication.class, args);
	}
}
