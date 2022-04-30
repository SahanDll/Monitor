package com.dev.mon.service.impl;

import com.dev.mon.db.repository.CardRepository;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.sql.DataSource;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CashDetailsImplTest {

    @InjectMocks
    CashDetailsImpl cashDetails;

    @Mock
    CardRepository cardRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCashDetails() {
        //when(cashDetails.getBalance("sahan")).thenReturn(Double.valueOf("12.50"));
        System.out.println(cashDetails.getBalance("sahan"));
        System.out.println(cashDetails.getCashDetails("sahan"));
        CashDetailsImpl cm = new CashDetailsImpl();
        System.out.println(cm.getCashDetails("deva"));

        assertEquals("sahan with balance : "+Double.valueOf("12.50"), cashDetails.getCashDetails("sahan"));
        verify(cashDetails, times(1)).getBalance("sahan");
        verify(cashDetails, times(1)).getCashDetails("sahan");
    }

    @Ignore
    @Test
    public void getSum() {
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource("jdbc:oracle:thin:@172.29.76.104:1521:MAYALSTYLE",
                "mayasit", "mayasit123");
        return ds;
    }

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        };
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }

}