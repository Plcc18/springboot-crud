package com.example.springboot_crud.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API CRUD Spring Boot",
                version = "1.0",
                description = "API para gerenciamento de produtos com Spring Boot"
        )
)
public class OpenApiConfig {
}
