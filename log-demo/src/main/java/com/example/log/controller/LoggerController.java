package com.example.log.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 日志
@RequestMapping("/log")
@RestController
public class LoggerController {

    // 获取日志对象
    // 注: Logger对象是属于org.slf4j包下的
    private final static Logger log = LoggerFactory.getLogger(LoggerController.class);

    @RequestMapping("/printLog")
    public String printLog() {
        // 日志对象打印日志
        log.info("打印日志");
        return "打印日志";
    }

    /**
     * 打印不同的日志级别
     */
    @RequestMapping("/printLogLevel")
    public String printLogLevel() {
        log.trace("这是trace级别");
        log.debug("这是debug级别");
        log.info("这是info级别");
        log.warn("这是warn级别");
        log.error("这是error级别");
        // fatal没有, 一旦fatal, 系统崩溃
        return "打印不同的日志级别";
    }


    @RequestMapping("/getInfo")
    public String getInfo() {
        // 使用println打印日志, 由println打印的日志信息和系统日志对比来看, 差了很多信息
        System.out.println("打印日志");
        return "打印日志";
    }
}