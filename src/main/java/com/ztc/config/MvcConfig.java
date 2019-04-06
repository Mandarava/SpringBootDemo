package com.ztc.config;

import com.ztc.interceptor.PerformanceInterceptor;
import com.ztc.utils.MessageConverterUtil;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer {

/*    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(24 * 60 * 60); // http://localhost:8089/swagger-ui.html
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(24 * 60 * 60);
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/").setCachePeriod(24 * 60 * 60);
    }*/

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        List<HttpMessageConverter<?>> messageConvertersNew = new ArrayList<>();
        for (HttpMessageConverter httpMessageConverter : converters) {
            if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                messageConvertersNew.add(MessageConverterUtil.fastJsonHttpMessageConverter());
            } else {
                messageConvertersNew.add(httpMessageConverter);
            }
        }
        converters.clear();
        converters.addAll(messageConvertersNew);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformanceInterceptor()).addPathPatterns("/**");
    }
}
