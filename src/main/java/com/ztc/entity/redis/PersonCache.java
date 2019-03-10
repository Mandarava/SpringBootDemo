package com.ztc.entity.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zt 2019/3/10 13:46
 */
@RedisHash(value = "person", timeToLive = 600)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonCache {

    @Id
    private Integer id;

    @Indexed
    private String name;

    private Integer age;

}