package com.ztc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@EnableCaching(proxyTargetClass = true)
@MapperScan("com.ztc.mapper.**")
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootDemoApplication.class);
        app.addListeners(new ApplicationPidFileWriter("./bin/app.pid"));
        app.run(args);
    }

}
