package com.microservices.apigw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/books/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://books"))
                .route(r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))
                .route(r -> r.path("/orders/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://bookstore"))
                .route(r -> r.path("/users/**")
                        //.filters(f -> f.filter(filter))
                        .uri("lb://bookuser"))
                .build();
    }
}
