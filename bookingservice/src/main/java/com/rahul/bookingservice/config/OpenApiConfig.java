package com.rahul.bookingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI inventoryServiceApi() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Booking Service API")
                        .description("Booking Service API Documentation")
                        .version("v1.0.0"));

    }
}

