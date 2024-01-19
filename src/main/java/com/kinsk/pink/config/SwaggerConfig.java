//package com.kinsk.pink.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springdoc.core.GroupedOpenApi;
//import org.springdoc.core.SwaggerUiConfigParameters;
//import org.springdoc.core.SwaggerUiConfigProperties;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Bean
//    public GroupedOpenApi customApi() {
//        return GroupedOpenApi.builder()
//                .group("custom-api")
//                .pathsToMatch("/api/custom/**")
//                .build();
//    }
//
//    @Bean
//    public SwaggerUiConfigParameters swaggerUiConfigParameters() {
//        return SwaggerUiConfigParametersBuilder.builder()
//                .withDefaultUrl("/api/custom/api-docs")
//                .build();
//    }
//}
