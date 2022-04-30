package com.dev.mon.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class CommonConfiguration {


    
    @Bean()
    MessageSource msgSouceBundle()
    {
        ResourceBundleMessageSource rbm = new ResourceBundleMessageSource();
        rbm.setBasenames("messages/messages", "messages");
        rbm.setDefaultEncoding("UTF-8");
        rbm.setUseCodeAsDefaultMessage(true);
        rbm.setFallbackToSystemLocale(true);
        return rbm;
    }
    
    
}
