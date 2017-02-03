package com.ztc.dynamicLogLevel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by zt on 2017/2/3.
 */
public class AlterLogEndpoint extends AbstractEndpoint<Boolean> {

    AtomicBoolean atomicBoolean = new AtomicBoolean();

    private Logger logger = LoggerFactory.getLogger(getClass());

    public AlterLogEndpoint() {
        super("alterLog", true, true);
    }

    @Override
    public Boolean invoke() {
        try {
            logger.info("change log no effect, please use alterLog/{level}?package=xxx.yyy.zzz");
            return atomicBoolean.getAndSet(!atomicBoolean.get());
//            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }
}
