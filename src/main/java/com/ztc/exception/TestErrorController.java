package com.ztc.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zt on 2017/2/3.
 */
@Controller
public class TestErrorController {

    @RequestMapping("/exception")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/exception/business")
    public String json() throws BusinessException {
        throw new BusinessException("发生 BusinessException");
    }
}
