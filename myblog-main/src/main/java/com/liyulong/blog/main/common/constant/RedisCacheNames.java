package com.liyulong.blog.main.common.constant;

/**
 * RedisCacheNames
 * 缓存的名称静态变量
 * 主要三个注解详情 http://www.manongjc.com/article/62169.html
 * 如果没有整合redis，会缓存到本地中
 *
 * 加入redis的starter后springboot会自动识别并使用RedisCacheConfiguration
 * 将数据缓存到redis中
 * <dependency>
 *             <groupId>org.springframework.boot</groupId>
 *             <artifactId>spring-boot-starter-data-redis</artifactId>
 * </dependency>
 */
public class RedisCacheNames {

    public final static String PROFIX = "MYBLOG:";

    /**
     * 文章缓存空间名称
     */
    public final static String ARTICLE = PROFIX + "ARTICLE";
    /**
     * 图书缓存空间名称
     */
    public final static String BOOK = PROFIX + "BOOK";

    /**
     * 友情链接列表
     */
    public final static String LINK = PROFIX + "LINK";

    /**
     * 推荐列表
     */
    public final static String RECOMMEND = PROFIX + "RECOMMEND";

    /**
     * 标签列表
     */
    public final static String TAG = PROFIX + "TAG";

    /**
     * 分类列表
     */
    public final static String CATEGORY = PROFIX +"CATEGORY";

    /**
     * 时光轴
     */
    public static final String TIMELINE =  PROFIX +"TIMELINE";

}
