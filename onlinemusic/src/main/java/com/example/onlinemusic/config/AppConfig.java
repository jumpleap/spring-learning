package com.example.onlinemusic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录之后才能当问其他页面
        LoginInterceptor loginInterceptor = new LoginInterceptor();

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns("/js/**.js") // 除了js文件
                .excludePathPatterns("/images/**") // 除了图片文件
                .excludePathPatterns("/css/**.css") // 除了css文件
                .excludePathPatterns("/fonts/**") // 除了fonts文件夹下的文件
                .excludePathPatterns("/player/**") // 除了player文件夹下的文件
                .excludePathPatterns("/login.html") // 除了登录页面
                .excludePathPatterns("/user/login"); // 除了登录接口
    }
}
