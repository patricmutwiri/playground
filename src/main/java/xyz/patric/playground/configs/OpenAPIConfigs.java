/*
 * Copyright (c) 2022-2023.
 * @author Patrick Mutwiri on 1/31/23, 6:01 PM
 */

package xyz.patric.playground.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfigs {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Playground Service")
                    .description("Playground Service")
                    .version("1.0.0")
                    .contact(
                        new Contact().name("Patrick Mutwiri")
                            .email("dev@patric.xyz")
                            .url("https://github.com/patricmutwiri")
                    )
                        .license(new License()
                                .name("MIT")
                        )
            )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("Repo")
                                .url("https://github.com/patricmutwiri")
                );
    }
}