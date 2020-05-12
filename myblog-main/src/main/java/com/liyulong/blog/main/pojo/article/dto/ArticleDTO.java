package com.liyulong.blog.main.pojo.article.dto;

import com.liyulong.blog.main.pojo.article.Article;
import com.liyulong.blog.main.pojo.operation.Tag;
import lombok.Data;

import java.util.List;

@Data
public class ArticleDTO extends Article {

    private List<Tag> tagList;

}
