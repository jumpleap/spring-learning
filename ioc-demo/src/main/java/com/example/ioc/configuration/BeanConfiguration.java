package com.example.ioc.configuration;

import com.example.ioc.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// 方法注解要搭配类注解进行使用
@Component
public class BeanConfiguration {
    // 方法注解: bean名称就是方法名
    // 指定默认的对象, 依赖注入时默认使用user1
    @Primary
    @Bean
    public User user1() {
        User user = new User();
        user.setName("张三");
        user.setAge(18);
        return user;
    }

    @Bean
    public User user2() {
        User user = new User();
        user.setName("李四");
        user.setAge(17);
        return user;
    }

    // 对bean进行重命名, 重命名后不能使用原来的方法名了
    @Bean("u1")
    public User user3() {
        User user = new User();
        user.setName("王二");
        user.setAge(16);
        return user;
    }
}
