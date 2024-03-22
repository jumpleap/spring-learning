package com.example.log.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/logger")
@RestController
public class LogController {
    @RequestMapping("/print")
    public String log() {
        log.info("使用@Slf4j打印日志");
        return "使用@Slf4j打印日志";
    }
}
