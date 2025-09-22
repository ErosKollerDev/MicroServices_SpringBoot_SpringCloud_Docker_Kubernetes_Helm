package com.eazybytes.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        return serverHttpSecurity.authorizeExchange(
                        exchange ->
//                        exchange.anyExchange().authenticated()
                                exchange.pathMatchers(HttpMethod.GET).permitAll()
//                                        .pathMatchers("/gateway/accounts/**").authenticated()
                                        .pathMatchers("/gateway/accounts/**").hasRole("ACCOUNTS")
//                                        .pathMatchers("/gateway/cards/**").authenticated()
                                        .pathMatchers("/gateway/cards/**").hasRole("CARDS")
//                                        .pathMatchers("/gateway/loans/**").authenticated()
                                        .pathMatchers("/gateway/loans/**").hasRole("LOANS")
                )
                .oauth2ResourceServer(oAuth2ResourceServerSpec ->
                        oAuth2ResourceServerSpec
//                                .jwt(Customizer.withDefaults())
                                .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(myCustomGrantedAuthoritiesExtractor()))
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }


    private Converter<Jwt, Mono<AbstractAuthenticationToken>> myCustomGrantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakRoleConverter());

        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
