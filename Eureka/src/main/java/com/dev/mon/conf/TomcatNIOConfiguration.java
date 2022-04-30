package com.dev.mon.conf;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class TomcatNIOConfiguration implements WebServerFactoryCustomizer<TomcatServletWebServerFactory>
{

    
    public static final String NIO2 = "org.apache.coyote.http11.Http11Nio2Protocol";
    public static final String NIO = "org.apache.coyote.http11.Http11NioProtocol";
    public static final String APR = "org.apache.coyote.http11.Http11AprProtocol";
    
    @Override
    public void customize(TomcatServletWebServerFactory factory)
    {
        // customize the factory here
        factory.setProtocol(NIO2);
    }
}