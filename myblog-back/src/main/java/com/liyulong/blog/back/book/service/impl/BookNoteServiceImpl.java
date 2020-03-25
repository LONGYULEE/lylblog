package com.liyulong.blog.back.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.back.book.service.BookNoteService;
import com.liyulong.blog.main.mapper.book.BookNoteMapper;
import com.liyulong.blog.main.pojo.book.BookNote;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 笔记 服务实现类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Service
public class BookNoteServiceImpl extends ServiceImpl<BookNoteMapper, BookNote> implements BookNoteService {

}
