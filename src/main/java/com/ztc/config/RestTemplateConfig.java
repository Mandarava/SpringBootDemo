package com.ztc.config;

import com.ztc.support.CustomConnectionKeepAliveStrategy;
import com.ztc.utils.MessageConverterUtil;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RestTemplateConfig {

    @Value("${http.readTimeout}")
    private int readTimeout;

    @Value("${http.connectTimeout}")
    private int connectTimeout;

    @Value("${http.connectionRequestTimeout}")
    private int connectionRequestTimeout;

    @Value("${http.maxConnection}")
    private int maxConnection;

    @Bean("restTemplate")
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        List<HttpMessageConverter<?>> messageConvertersNew = new ArrayList<>();
        for (HttpMessageConverter httpMessageConverter : messageConverters) {
            if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                messageConvertersNew.add(MessageConverterUtil.fastJsonHttpMessageConverter());
            } else {
                messageConvertersNew.add(httpMessageConverter);
            }
        }
        restTemplate.setMessageConverters(messageConvertersNew);
        return new RestTemplate(factory);
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        PoolingHttpClientConnectionManager connectionManager =
                new PoolingHttpClientConnectionManager(15, TimeUnit.SECONDS);
        connectionManager.setMaxTotal(maxConnection);
        connectionManager.setDefaultMaxPerRoute(maxConnection);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .disableAutomaticRetries()
                // 有 Keep-Alive 认里面的值，没有的话永久有效
                //.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                // 换成自定义的
                .setKeepAliveStrategy(new CustomConnectionKeepAliveStrategy())
                // .setProxy(new HttpHost("127.0.0.1", 8888))
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        // global setting
        requestFactory.setReadTimeout(readTimeout);
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setConnectionRequestTimeout(connectionRequestTimeout);
        requestFactory.setBufferRequestBody(false);
        return requestFactory;
    }

    public SSLConnectionSocketFactory sslConnectionSocketFactory() {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = null;
        try {
            sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            log.error(e.getMessage(), e);
        }
        return new SSLConnectionSocketFactory(sslContext);
    }

}
