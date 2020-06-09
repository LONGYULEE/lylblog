package com.liyulong.blog.index.common.annotation;

import org.springframework.data.elasticsearch.annotations.Document;

import java.lang.annotation.*;

//注解配置
@Target(ElementType.METHOD) //用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
/**
 * @Retention 作用是定义被它所注解的注解保留多久，一共有三种策略，定义在RetentionPolicy枚举中.
 * 从注释上看：
 * source：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；被编译器忽略
 * class：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期
 * runtime：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 * @Documented 注解表明这个注解应该被 javadoc工具记录. 默认情况下,javadoc是不包括注解的.
 * 但如果声明注解时指定了 @Documented,则它会被 javadoc 之类的工具处理,
 * 所以注解类型信息也会被包括在生成的文档中，是一个标记注解，没有成员。
 */
@Documented
public @interface LogLike {


    /**
     * 注解类型元素
     * 访问修饰符必须为public，不写默认为public；
     * 该元素的类型只能是基本数据类型、String、Class、枚举类型、注解类型（体现了注解的嵌套效果）以及上述类型的一位数组；
     * 该元素的名称一般定义为名词，如果注解中只有一个元素，请把名字起为value（后面使用会带来便利操作）；
     * ()不是定义方法参数的地方，也不能在括号中定义任何参数，仅仅只是一个特殊的语法；
     * default代表默认值，值必须和第2点定义的类型一致；
     * 如果没有默认值，代表后续使用注解时必须给该类型元素赋值。
     *
     * 相当于 @LogLike(type = "")
     */
    String type();

}
