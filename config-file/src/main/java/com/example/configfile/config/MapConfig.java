package com.example.configfile.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
// @ConfigurationProperties() 括号中yml配置文件的Map名
@ConfigurationProperties("maptypes")
@Component
@Data
public class MapConfig {
    private Map<String, Integer> map;
}
