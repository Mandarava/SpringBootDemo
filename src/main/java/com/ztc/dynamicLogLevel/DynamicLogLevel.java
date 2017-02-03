package com.ztc.dynamicLogLevel;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zt on 2017/2/3.
 */
@RestController
public class DynamicLogLevel {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/loglevel", method = RequestMethod.GET)
    public String testLogLevel() {
        logger.debug("Logger Level ：DEBUG");
        logger.info("Logger Level ：INFO");
        logger.error("Logger Level ：ERROR");
        return "";
    }

}
