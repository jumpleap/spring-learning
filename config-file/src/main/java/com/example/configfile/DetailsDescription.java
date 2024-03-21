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

        @Value值加单双引号
            1. 字符串默认不用加上单引号或者双引号
            2. 单引号会使原来特殊字符失去含义, 变成普通的字符
            3. 双引号会转义里面的特殊字符串, 即特殊字符会表示自身的含义

        yml配置文件中对象的获取: @ConfigurationProperties注解获取
        yml配置文件中集合的获取: @ConfigurationProperties注解获取
        yml配置文件中Map的获取: @ConfigurationProperties注解获取
        注:@ConfigurationProperties() 括号中跟yml对应的名称

        yml的优点与缺点
            优点:
                1. 可读性高, 写法简单, 易于理解
                2. 支持更多的数据类型, 如: 数组, 对象, List, Map等
                3. 支持更多的编程语言, 不止Java中可用
            缺点:
                1. 不适合写复杂的配置文件
                2. 对格式有较强的要求(key: value), 冒号后面必须跟空格

        总结:
            1. properties是以key=value的形式配置的键值类型的配置⽂件, yml使⽤的是树形配置⽅式.
            2. 读取配置⽂件内容, 使⽤@Value注解, 注解内使⽤" ${} "的格式读取.
            3. yml层级之间使⽤换⾏缩进的⽅式配置, key和value之间使⽤": "(英⽂冒号)加空格的⽅式设置,
               并且空格不可省略.
            4. properties为早期并且默认的配置⽂件格式, 其配置存在⼀定的冗余数据,
               使⽤yml可以很好的解决数据冗余的问题, 但不适合复杂配置.
            5. yml可以和properties共存，但⼀个项⽬中建议使⽤⼀种配置类型⽂件.
     */
}
