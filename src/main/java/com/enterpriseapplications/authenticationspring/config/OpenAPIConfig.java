package com.enterpriseapplications.authenticationspring.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Progetto Enterprise Applications",
                version = "1.0.0",
                description = "Documentazione REST API per Authorization Server Enterprise-Applications'")
)
@SecurityScheme(name = "Authorization",type = SecuritySchemeType.HTTP,in = SecuritySchemeIn.HEADER,scheme = "bearer",bearerFormat = "JWT")
@Configuration
public class OpenAPIConfig {
}
