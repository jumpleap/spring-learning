package com.example.configfile.controller;

import com.example.configfile.config.ListConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/list")
@RestController
public class ListController {
    @Autowired
    private ListConfig listConfig;

    @RequestMapping("/readList")
    public String readList() {
        return listConfig.toString();
    }
}
