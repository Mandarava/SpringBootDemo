package com.ztc.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.ztc.support.BytesToMoneyConverter;
import com.ztc.support.MoneyToBytesConverter;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.convert.CustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
@EnableCaching(proxyTargetClass = true)
@EnableRedisRepositories
public class RedisConfig extends CachingConfigurerSupport {

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        RedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // value
        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(connectionFactory);
        return stringRedisTemplate;
    }

    @Bean
    public CustomConversions redisCustomConversions() {
        return new CustomConversions(
                Arrays.asList(new MoneyToBytesConverter(), new BytesToMoneyConverter()));
    }

//    @Bean
//    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
//        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        // container.setTaskExecutor(Executors.newFixedThreadPool(4));
//        Map<String, TopicListener> messageSubscribers = SpringUtil.getBeansOfType(TopicListener.class);
//        if (MapUtils.isNotEmpty(messageSubscribers)) {
//            for (Map.Entry<String, TopicListener> entry : messageSubscribers.entrySet()) {
//                TopicListener messageSubscriber = entry.getValue();
//                container.addMessageListener(new MessageListenerAdapter(messageSubscriber), messageSubscriber.getTopic());
//            }
//        }
//        return container;
//    }

}
