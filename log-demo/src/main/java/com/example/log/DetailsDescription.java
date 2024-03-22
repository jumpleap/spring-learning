package com.example.log;

// 知识整合
public class DetailsDescription {
    /*
        日志的用途
            1. 系统监控: 监控现在⼏乎是⼀个成熟系统的标配, 我们可以通过⽇志记录这个系统的运⾏状态,
                        每⼀个⽅法的响应时间, 响应状态等, 对数据进⾏分析, 设置不同的规则, 超过阈值时进⾏报警.
            2. 数据采集: 数据采集是⼀个⽐较⼤的范围, 采集的数据可以作⽤在很多⽅⾯, ⽐如数据统计, 推荐排序等.
            3. 日志审计: 通过系统⽇志分析，可以判断⼀些⾮法攻击, ⾮法调⽤，以及系统处理过程中的安全隐患

        SpringBoot中内置了日志框架slf4j, 我们可用直接调用使用
        slf4j就是一个日志门面

        门面模式(外观模式)
            定义: 门面模式提供了一个统一的接口, 用来访问子系统中的一群接口
            角色:
                1) 外观角色(Facade): 也称为门面角色, 系统对外的统一接口
                2) 子系统角色(SubSystem): 可以同时有一个或多个SubSystem, 每个SubSystem都不是一个单独的类,
                    而是一个类的集合, SubSystem并不知道Facade的存在, 对于SubSystem而言, Facade只是一个客户端
            优点:
                1) 减少了系统的相互依赖, 实现了客户端与子系统的耦合关系, 这使得子系统的变化不影响调用它的客户端
                2) 提高了灵活性, 简化了客户端对子系统的使用难度, 客户端无需关心子系统的实现方式, 只需要和门面模式交互即可
                3) 提高了安全性, 可以灵活设定访问权限, 不在门面对象中开通的方法, 就无法访问

        日志详解:
            2024-03-22 11:03:17.441  INFO  18096 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
              日期时间(精确到ms)      日志级别 进程ID            线程名                 Logger名(常为源代码的类名)               日志内容

        日志级别:
            TRACE -> DEBUG -> INFO -> WARN -> ERROR -> FATAL : 从左往右依次增高
            TRACE: 追踪信息, 比DEBUG更细粒度的信息时间(一般使用DEBUG代替)
            DEBUG: 调试信息, 需要调试的时候打印关键信息
            INFO:  普通信息, 用于记录应用程序正常运行时的一些信息
            WARN:  警告信息, 不影响使用, 但需要注意的问题
            ERROR: 错误信息, 级别较高的错误日志信息, 但仍然不影响系统的继续运行
            FATAL: 致命信息, 表示需要立即被处理的系统级错误
            注: 默认的级别是info级别, 只能打印info即以上的级别

        日志持久化(保存日志文件):
            1. 配置日志文件名: logging.file.name, 自定义文件名
            2. 配置日志的存储目录: logging.file.path, 文件名只能默认: spring.log
            注: logging.file.name 和 logging.file.path都配置的情况下, 只能生效一个, 以logging.file.name为准

        日志文件分割:
            ⽇志分割后的⽂件名:
                logging.logback.rollingpolicy.file-name-pattern: ${LOG_FILE}.%d{yyyy-MM-dd}.%i.gz
            ⽇志⽂件超过这个⼤⼩就⾃动分割:
                logging.logback.rollingpolicy.max-file-size: 10MB

        简单的日志输出:
            使用lombok依赖, lombok中自带@Slf4j来进行日志的打印
     */
}
