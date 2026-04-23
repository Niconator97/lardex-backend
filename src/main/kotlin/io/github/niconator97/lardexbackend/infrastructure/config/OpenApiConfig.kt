package io.github.niconator97.lardexbackend.infrastructure.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun lardexOpenApi(): OpenAPI =
        OpenAPI()
            .info(
                Info()
                .title("Lardex Backend API")
                    .description("A Spring Boot Swagger API for Lardex Backend")
                    .version("v1.0.0")
                    .contact(
                        Contact()
                            .name("Lardex")
                    )
                    .license(
                        License()
                            .name("Proprietary")
                    )
            )
            .addServersItem(
                Server()
                    .url("http://localhost:8080")
                    .description("Local development")
            )
}