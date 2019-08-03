package com.stackroute.trackservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
//@EnableSwagger2 annotation is used to enable the Swagger2 for our Spring Boot application.
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                //select() method returns an instance of ApiSelectorBuilder.
                .select()
                .apis(RequestHandlerSelectors.any())//any() for both will make documentation for entire API available through Swagger
                .paths(PathSelectors.any())
                .build();
    }
}
