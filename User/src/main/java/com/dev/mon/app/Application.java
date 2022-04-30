package com.dev.mon.app;

import com.dev.mon.conf.CommonConfiguration;
import com.dev.mon.proxy.CashProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.dev.mon")
@EnableEurekaClient
@EnableSwagger2
@EnableTransactionManagement
@EnableFeignClients(basePackageClasses = {CashProxy.class})
@Import({CommonConfiguration.class})
@RibbonClient("USER")
public class Application {
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket swaggerApi()
    {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(true).build();
        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(aParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.maybank2u.life.app.controller")).paths(PathSelectors.any()).build()
                .directModelSubstitute(LocalDate.class, String.class).globalOperationParameters(aParameters)
                .genericModelSubstitutes(new Class[] { ResponseEntity.class });
    }
}
