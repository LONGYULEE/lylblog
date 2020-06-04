package com.liyulong.blog.main.pojo.article.vo;

import com.liyulong.blog.main.pojo.article.Article;
import com.liyulong.blog.main.pojo.operation.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

//@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleVO extends Article {
    /**
     * 所属分类，以逗号分隔
     */
    private String categoryListStr;

    /**
     * 所属标签
     */
    private List<Tag> tagList;
}
