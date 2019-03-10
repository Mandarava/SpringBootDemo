package com.ztc.repository;

import com.ztc.entity.Person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zt on 2016/12/24.
 */
public interface PersonRepository extends JpaRepository<Person,Integer>{

    List<Person> findByAge(Integer age);
}
