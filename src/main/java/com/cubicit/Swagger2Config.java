package com.cubicit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

@Bean
public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.withClassAnnotation(SwaggerCtrl.class))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
}

private ApiInfo apiInfo() {
    ApiInfo apiInfo = new ApiInfo("Spring Boot2.x", "Some custom description of API.", "API 2.0", "Terms of service", "technohunk100@gmail.com", "License of API", "API license URL");
    return apiInfo;
}
}