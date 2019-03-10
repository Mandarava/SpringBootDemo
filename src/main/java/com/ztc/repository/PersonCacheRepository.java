package com.ztc.repository;

import com.ztc.entity.redis.PersonCache;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by zt 2019/3/10 13:50
 */
public interface PersonCacheRepository extends CrudRepository<PersonCache, Long> {
    Optional<PersonCache> findOneByName(String name);
}
