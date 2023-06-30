package com.enterpriseapplications.authenticationspring.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Progetto Enterprise Applications",
                version = "1.0.0",
                description = "Documentazione REST API per Authorization Server Enterprise-Applications'")
)
@Configuration
public class OpenAPIConfig {
}
