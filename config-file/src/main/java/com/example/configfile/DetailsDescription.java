package com.example.configfile;

// 知识点整合
public class DetailsDescription {
    /*
        配置⽂件主要是为了解决硬编码带来的问题, 把可能会发⽣改变的信息, 放在⼀个集中的地⽅,
        当我们启动某个程序时, 应⽤程序从配置⽂件中读取数据, 并加载运⾏

        SpringBoot的配置文件
            1. application.properties
            2. application.yml(为第三种的简化版, 两者使用方式相同)
            3. application.yaml
            注: 当应⽤程序启动时, SpringBoot会⾃动从classpath路径找到并加载
                application.properties 和 application.yaml 或者 application.yml ⽂件.

        application.properties 和 application.yml可以共存, 当两者共存时, 两个配置均会加载,
        若配置文件有冲突, 则以.properties为主, 即.properties的优先级更高; 但不建议两个文件共存

        properties配置文件
            1. properties配置文件是SpringBoot的默认的配置文件
            2. properties文件中配置项是以键值对的形式配置的, key 和 value 之间是以 "="连接的

        在项目中, 想要主动的读取配置文件中的内容, 可以使用@Value注解来实现
        @Value注解使用 "${}"的格式读取

        yml基础语法: 它的基础语法是"key: value".
                    key和value之间使⽤英⽂冒号加空格的⽅式组成，空格不可省略
        yml的读取配置的方式和properties相同, 使用@Value读取

     */
}
