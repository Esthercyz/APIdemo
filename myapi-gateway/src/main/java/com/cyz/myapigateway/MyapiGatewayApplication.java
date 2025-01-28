package com.cyz.myapigateway;

import com.yupi.springbootinit.provider.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.stereotype.Service;

@SpringBootApplication
@EnableDubbo
@Service
public class MyapiGatewayApplication {
    @DubboReference
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(MyapiGatewayApplication.class, args);
    }

}
