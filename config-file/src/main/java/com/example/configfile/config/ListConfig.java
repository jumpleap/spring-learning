package com.example.configfile.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

// @ConfigurationProperties() 括号中跟yml中的List名
@ConfigurationProperties("list")
@Component
@Data
public class ListConfig {
    private List<String> name;
}
