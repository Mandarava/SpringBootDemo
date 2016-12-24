package com.ztc.dao;

import com.ztc.entity.Person;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zt on 2016/12/24.
 */
@Repository
public interface PersonMapper {
    List<Person> findPersonList();
}
