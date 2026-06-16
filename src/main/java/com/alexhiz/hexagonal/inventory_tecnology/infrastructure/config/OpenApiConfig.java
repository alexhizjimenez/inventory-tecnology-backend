package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inventory Tecnology")
                        .version("1.0")
                        .description("Sistema de gestión de inventario tecnológico con arquitectura hexagonal.")
                        .contact(new Contact()
                                .name("Alexis Jimenez")
                                .email("alexhizdev@gmail.com")));
    }
}
