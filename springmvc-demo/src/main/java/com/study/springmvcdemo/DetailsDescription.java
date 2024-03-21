package com.study.springmvcdemo;

// 知识点整合
public class DetailsDescription {
    /*
        SpringMVC的各种注解
            a. @RequestMapping: 路由映射
            b. @RequestParam: 后端参数重命名
            c. @RequestBody: 接收JSON类型的参数
            d. @PathVariable: 接收路径参数
            e. @RequestPart: 上传⽂件
            f. @ResponseBody: 返回数据
            g. @CookieValue: 从Cookie中获取值
            h. @SessionAttribute: 从Session中获取值
            i. @RequestHeader: 从Header中获取值
            j. @Controller: 定义⼀个控制器, Spring框架启动时加载, 把这个对象交给Spring管理.默认返回视图.
            k. @RestController: @ResponseBody + @Controller -> 返回数据

        Cookie 和 Session的区别和联系
            1. Cookie和Session都是会话机制, Cookie是客⼾端机制,Session是服务端机制.⼆者通过SessionId来关联.
            2. SpringMVC内置HttpServletRequest, HttpServletResponse两个对象. 需要使⽤时,直接在⽅法中添加对应参数即可.
            3.Cookie和Session可以从HttpServletRequest中来获取, 也可以直接使⽤HttpServletResponse设置Http响应状态码.

        规范
            1. 类名使⽤⼤驼峰⻛格，但以下情形例外：DO/BO/DTO/VO/AO
            2. ⽅法名、参数名、成员变量、局部变量统⼀使⽤⼩驼峰⻛格
            3. 包名统⼀使⽤⼩写，点分隔符之间有且仅有⼀个⾃然语义的英语单词.
            ⼤驼峰: 所有单词⾸字⺟都需要⼤写, ⼜叫帕斯卡命名法, ⽐如: MessageInfo
            ⼩驼峰: 除了第⼀个单词，其他单词⾸字⺟⼤写, ⽐如: messageInfo

        三层架构
            1. Controller: 控制层, 接收前端发送的请求, 对请求进行处理, 并响应数据
            2. Service: 业务逻辑层, 处理具体的业务逻辑
            3. Dao: 数据访问层, 也称为持久层, 负责数据访问操作, 包括数据的增删查改

        软件设计原则：⾼内聚低耦合.
            ⾼内聚指的是：⼀个模块中各个元素之间的联系的紧密程度，如果各个元素(语句、程序段)之间的联系程度越⾼，
                       则内聚性越⾼，即"⾼内聚"。
            低耦合指的是：软件中各个层、模块之间的依赖关联程序越低越好。修改⼀处代码, 其他模块的代码改动越少越好.
     */
}
