package com.liyulong.blog.main.mapper.book;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyulong.blog.main.pojo.book.BookNote;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 笔记 Mapper 接口
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Mapper
public interface BookNoteMapper extends BaseMapper<BookNote> {

}
