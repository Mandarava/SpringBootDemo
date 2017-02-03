package com.ztc.dynamicLogLevel;

import com.google.common.collect.Maps;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by zt on 2017/2/3.
 */
public class AlterLogMvcEndpoint extends EndpointMvcAdapter {

    public AlterLogMvcEndpoint(AlterLogEndpoint delegate) {
        super(delegate);
    }

    @GetMapping(value = "/{level}/{packageName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object alterLog(
            @PathVariable String packageName,
            @PathVariable String level) {
        Map<String, String> result = Maps.newHashMap();
        boolean success = true;
        try {
            // change log level
            Logger log = LoggerFactory.getLogger(packageName);
            Configurator.setLevel(log.getName(), Level.toLevel(level));

//            LogbackLoggingSystem logbackLoggingSystem = new LogbackLoggingSystem(this.getClass().getClassLoader());
//            logbackLoggingSystem.setLogLevel(packageName, LogLevel.valueOf(level));
        } catch (Exception e) {
            success = false;
        }
        result.put("success", String.valueOf(success));
        return result;
    }
}
