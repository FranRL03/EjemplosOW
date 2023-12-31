package com.example.DocumentacionApi.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/*
http://localhost:8080/v3/api-docs
http://localhost:8080/swagger-ui/index.html
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info()
                    .title("Employees API")
                    .description("API REST empleados de la empresa X")
                    .version("v0.0.1")
                    .license(new License().name("Apache 2.0").url("http://www.example.com/")))
                .externalDocs(new ExternalDocumentation()
                        .description("Wiki Docs")
                        .url("http://www.example.com/"));

    }
}
