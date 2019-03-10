package com.ztc.config;

import com.ztc.utils.MessageConverterUtil;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
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
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setMaxConnTotal(maxConnection);
        httpClientBuilder.setMaxConnPerRoute(maxConnection);
        httpClientBuilder.setConnectionTimeToLive(5, TimeUnit.SECONDS);
        factory.setHttpClient(httpClientBuilder.build());
        factory.setReadTimeout(readTimeout);
        factory.setConnectTimeout(connectTimeout);
        factory.setConnectionRequestTimeout(connectionRequestTimeout);
        factory.setBufferRequestBody(false);
        // factory.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)));
        return factory;
    }

}
