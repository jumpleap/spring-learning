package com.example.ioc;

// 相关知识点描述
public class DetailsDescription {
    /*
        五大注解: @Controller, @Service, @Repository, @Configuration, @Component
        @Controller: 控制层, 接收请求, 对请求进行处理, 并进行响应
        @Service: 业务逻辑层, 处理具体的业务逻辑
        @Repository: 数据访问层, 也称为持久层, 负责数据访问操作
        @Configuration / @Component :配置层, 处理项目中的一些配置信息

        类注解之间的关系:
            查看@Controller, @Service, @Repository, @Configuration的源码发现,
            这些注解中都有一个注解@Component, 说明它们本身就属于@Component的子类, @Component是一个元注解,
            元注解就是可以注解其他类注解的注解, 如: @Controller, @Service等, 这些注解被称为@Component的衍生注解

        方法注解: @Bean
            @Bean要搭配类注解进行使用, 这样才会被spring进行管理

        bean的命名
            1. 五⼤注解存储的bean
                ①前两位字⺟均为⼤写, bean名称为类名
                ②其他的为类名⾸字⺟⼩写
                ③通过value属性命名 @Controller(value = "bean命名")
            2. @Bean注解存储的bean
                ①bean名称为⽅法名
                ②通过name属性进行重命名 @Bean(name = {"bean命名"})

        扫描路径
            1. 使用五大注解声明的bean, 要想生效, 还需要配置扫描路径, 让Spring进行扫描到这些注解
            2. Spring启动类默认的扫描路径是当前包的类及子包的类
            3. 若是有其他类不在Spring的默认扫描路径下, 通过@ComponentScan来配置扫描路径

        DI(依赖注入)
            依赖注入是一个过程, 是指IoC容器在创建Bean时提供运行时所依赖的资源, 而资源指的就是对象

        依赖注入的方式
            1. 属性注入:
                使用@Autowired实现
            2. 构造方法注入:
                若只有一个构造方法, 无需使用@Autowired, 若有多个构造方法, 需使用@Autowired指定使用那个构造方法
            3. Setter注入:
                在设置set方法的时候需要加上@Autowired注解

        当存在多个对象时, @Autowired会存在问题, User存在非唯一的对象, 如何解决?
            1. @Primary:
                当存在多个相同类型的Bean注入时, 加上@Primary注解, 来确定默认的实现
            2. @Qualifier:
                指定当前要注入的bean对象, 在@Qualifier的value属性中, 指定注入的bean的名称
                @Qualifier不能单独使用, 必须配合@Qualifier使用
            3. @Resource:
                按照bean的名称进行注入, 通过name属性指定要注入的bean的名称

        @Autowired与@Resource的区别
            1. @Autowired是spring框架提供的注解, 而@Resource是JDK提供的注解
            2. @Autowired默认是按照类型注入, 而@Resource是按照名称注入, 相比于@Autowired来说,
               @Resource支持更多的参数设置, 例如name设置, 根据名称获取bean

        Spring, SpringBoot和SpringMVC的关系以及区别
            1. Spring: 简单来说, Spring是⼀个开发应⽤框架，什么样的框架呢，有这么⼏个标签：
               轻量级、⼀站式、模块化，其⽬的是⽤于简化企业级应⽤程序开发.
            2. SpringMVC: SpringMVC是Spring的⼀个⼦框架, Spring诞⽣之后, ⼤家觉得很好⽤, 于是按照MVC
               模式设计了⼀个MVC框架(⼀些⽤Spring解耦的组件), 主要⽤于开发WEB应⽤和⽹络接⼝，所以,SpringMVC是⼀个Web框架.
            3. SpringBoot: SpringBoot是对Spring的⼀个封装, 为了简化Spring应⽤的开发⽽出现的，中⼩型
               企业，没有成本研究⾃⼰的框架, 使⽤SpringBoot可以更加快速的搭建框架, 降级开发成本, 让开发
               ⼈员更加专注于Spring应⽤的开发，⽽⽆需过多关注XML的配置和⼀些底层的实现.
            总结: SpringMVC和SpringBoot都属于Spring，SpringMVC是基于Spring的⼀个MVC框架，
                 ⽽SpringBoot是基于Spring的⼀套快速开发整合包.

        常⻅注解有哪些? 分别是什么作⽤?
            1. WebUrl映射: @RequestMapping
            2. 参数接收和接⼝响应: @RequestParam, @RequestBody, @ResponseBody
            3. bean的存储: @Controller, @Service, @Repository, @Component, @Configuration,
            4. @Bean
                bean的获取: @Autowired, @Qualifier, @Resource, @Primary
 */
}
