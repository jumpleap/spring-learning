package com.example.configfile.controller;

import com.example.configfile.config.MapConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/map")
@RestController
public class MapController {
    // 注入对象
    @Autowired
    private MapConfig mapConfig;

    @RequestMapping("/readMap")
    public String readMap() {
        return mapConfig.toString();
    }
}
