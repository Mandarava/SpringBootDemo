package com.ztc.controller;

import com.ztc.dao.PersonMapper;
import com.ztc.entity.Person;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zt on 2016/12/24.
 */
@RestController
@MapperScan("com.ztc.dao")
public class PersonMybatisController {

    @Autowired
    private PersonMapper personMapper;

    @GetMapping(value = "/person2")
    public List<Person> findPersonList() {
        return personMapper.findPersonList();
    }

}
