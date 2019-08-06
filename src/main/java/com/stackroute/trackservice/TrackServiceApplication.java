package com.stackroute.trackservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
//@EnableSwagger2 annotation is used to enable the Swagger2 for our Spring Boot application.
@SpringBootApplication

@PropertySource("classpath:application-prod.properties")
public class TrackServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackServiceApplication.class, args);
	}

}
