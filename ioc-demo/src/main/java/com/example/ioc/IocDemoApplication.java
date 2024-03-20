package com.example.ioc;

import com.example.ioc.component.UserComponent;
import com.example.ioc.configuration.UserConfiguration;
import com.example.ioc.controller.UserController;
import com.example.ioc.controller.UserController2;
import com.example.ioc.controller.UserController3;
import com.example.ioc.model.User;
import com.example.ioc.repository.UserRepository;
import com.example.ioc.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

// 配置扫描路径
@ComponentScan({"com.example"})
// 启动类
@SpringBootApplication
public class IocDemoApplication {
    public static void main(String[] args) {
        // 获取Spring上下文(当前的运行环境)对象
        ApplicationContext context = SpringApplication.run(IocDemoApplication.class, args);
        // 从Spring上下文中获取对象
        UserController userController = context.getBean(UserController.class);
        // 使用对象
        userController.sayHi();

        /*
            获取bean的方式
         */
        // @Controller
        // 根据名称获取bean
        UserController userController1 = (UserController) context.getBean("userController");
        userController1.sayHi();
        System.out.println(userController1);

        // 根据类型获取bean
        UserController userController2 = context.getBean(UserController.class);
        userController2.sayHi();
        System.out.println(userController2);

        // 根据bean名称和类型获取bean
        UserController userController3 = context.getBean("userController", UserController.class);
        userController3.sayHi();
        System.out.println(userController3);

        // @Service
        UserService userService = (UserService) context.getBean("userService");
        userService.sayHi();

        // @Repository
        UserRepository bean = context.getBean(UserRepository.class);
        bean.sayHi();

        // @Component
        UserComponent userComponent = context.getBean("userComponent", UserComponent.class);
        userComponent.sayHi();

        // @Configuration
        UserConfiguration userConfiguration = context.getBean("userConfiguration", UserConfiguration.class);
        userConfiguration.sayHi();

        /*
            @Bean
         */
        // User user = (User)context.getBean(User.class);
        // System.out.println(user);

        // 定义多个对象
        // 当有多个对象的时候, 上述操作不满足了, 因为出现了两个对象
        // 使用@Bean针对的注解的方法名进行获取(使用方法名即可获取bean)
        User user1 = (User)context.getBean("user1");
        User user2 = (User)context.getBean("user2");
        System.out.println(user1);
        System.out.println(user2);

        // bean重命名后进行获取
        User u1 = (User)context.getBean("u1");
        System.out.println(u1);
        // 重命名之后不能使用原先的方法名
        // User user3 = (User)context.getBean("user3");
        // System.out.println(user3);

        // 依赖注入(构造器)
        UserController2 userController4 = context.getBean(UserController2.class);
        userController4.sayHi();

        // 依赖注入(setter方法)
        UserController3 userController5 = context.getBean(UserController3.class);
        userController5.sayHi();
    }
}
