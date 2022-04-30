package com.dev.mon.app;

import com.dev.mon.conf.CommonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableZuulProxy
@EnableSwagger2
@EnableHystrix
@EnableCircuitBreaker
@EnableFeignClients(basePackageClasses= {})
@Import(CommonConfiguration.class)
@RibbonClient("GATEWAY")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
