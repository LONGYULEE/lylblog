package com.liyulong.blog.main.common.constant;

/**
 * RedisCacheNames
 * 缓存的名称静态变量
 * 并不是 redis 缓存，是缓存在本地的
 * 主要三个注解详情 http://www.manongjc.com/article/62169.html
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
