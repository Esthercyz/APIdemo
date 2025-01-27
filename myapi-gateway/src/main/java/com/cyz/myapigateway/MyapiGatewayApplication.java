package com.cyz.myapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@SpringBootApplication
public class MyapiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyapiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/hi")
                        .uri("https://google.com"))
                .route("host_route", r -> r.path("/jump")
                        .uri("https://baidu.com"))
                .build();
    }
}
