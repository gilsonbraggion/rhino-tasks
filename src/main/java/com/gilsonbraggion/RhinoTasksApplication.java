package com.gilsonbraggion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RhinoTasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(RhinoTasksApplication.class, args);
	}

}
