package com.dev.mon.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.dev.mon")
//@EnableEurekaClient
//@EnableSwagger2
//@EnableTransactionManagement
//@EnableFeignClients()
//@RibbonClient("CACHE")
public class Application {

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .build();
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            String uriList [] = null;
            long delay = 0;
            boolean info = false;
            try {
                System.out.println("Input Url: " + args[0]);
                System.out.println("Input Delay: " + args[1]);
                System.out.println("Input Show Info: " + args[2]);
                uriList = args[0].split(",");
                delay = Long.parseLong(args[1]);
                info = Boolean.parseBoolean(args[2]);
            } catch (Exception e) {
                delay = 5000;
                info = false;
            }
            while(true){
                try {
                    Thread.sleep(delay);
                    for(String uri : uriList) {
                        try {
                            RestTemplate restTemplate = new RestTemplate();
                            HttpHeaders headers = new HttpHeaders();
                            headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
                            HttpEntity<String> request = new HttpEntity<String>(null, headers);
                            ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.DELETE, request, String.class);
                            if (info) {
                                System.out.println(result);
                            }
                            if (result.getStatusCode() == HttpStatus.OK) {
                                System.out.println("Instance deregister successful : "+uri);
                            }
                        } catch (Exception e) {

                        }
                        Thread.sleep(1000);
                    }
                } catch (RestClientException e) {
                    //System.out.println(e.getLocalizedMessage());
                } catch (InterruptedException e) {
                    //System.out.println(e.getLocalizedMessage());
                }
            }
        };
    }
}
