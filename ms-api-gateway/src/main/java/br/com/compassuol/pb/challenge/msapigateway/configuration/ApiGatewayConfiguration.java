package br.com.compassuol.pb.challenge.msapigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder locatorBuilder) {
        return locatorBuilder
                .routes()
                    .route(p -> p.path("/products/**").uri("lb://ms-products:8080/"))
                    .route(p -> p.path("/categories/**").uri("lb://ms-products:8080/"))
                    .route(p -> p.path("/users/**").uri("lb://ms-products:8080/"))
                .build();
    }
}
