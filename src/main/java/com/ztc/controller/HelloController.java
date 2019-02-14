package com.ztc.controller;


import com.ztc.constant.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zt on 2016/12/24.
 */
@RestController
@EnableAutoConfiguration
public class HelloController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private Properties properties;

    @GetMapping(value = "/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "Hello " + name;
    }

}
