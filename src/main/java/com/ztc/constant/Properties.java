package com.ztc.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by zt on 2016/12/24.
 */
@Component
@ConfigurationProperties(prefix = "server")
public class Properties {

    private String port;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }


}
