package com.kinsk.pink.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder()
                .group("com.kinsk.pink.controller")
                .pathsToMatch("/**")
                .build();
    }


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Project Pink Application")
                        .version("v. 01")
                        .description("The Project Pink Application is a platform designed for efficient management" +
                                "of product subscriptions. It incorporates three key entities, seamlessly integrated" +
                                "to streamline the subscription process.")
                        .contact(new Contact()
                                .name("Deise Kinsk")
                                .url("https://www.linkedin.com/in/deise-kinsk-profile/")
                                .email("kinsk@msn.com"))
                        .license(new License()
                                .name("License Creative Commons")
                                .url("https://creativecommons.org")
                                .identifier(" BY: credit must be given to the creator.\n" +
                                        "SA: Adaptations must be shared under the same terms.")));

    }


}

