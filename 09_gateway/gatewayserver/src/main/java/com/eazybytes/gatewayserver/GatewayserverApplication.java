package com.eazybytes.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(p -> p
                        .path("/gateway/accounts/**")
                        .filters(f -> f.rewritePath("/gateway/accounts/(?<segment>.*)", "/${segment}"))
                        .uri("lb://ACCOUNTS"))
                .route(p -> p
                        .path("/gateway/cards/**")
                        .filters(f -> f.rewritePath("/gateway/cards/(?<segment>.*)", "/${segment}"))
                        .uri("lb://CARDS"))
                .route(p -> p
                        .path("/gateway/loans/**")
                        .filters(f -> f.rewritePath("/gateway/loans/(?<segment>.*)", "/${segment}"))
                        .uri("lb://LOANS"))
                .build();
    }

}
