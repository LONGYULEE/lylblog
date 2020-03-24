package com.liyulong.myblog.back.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyulong.blog.main.mapper.book.BookMapper;
import com.liyulong.blog.main.pojo.book.Book;
import com.liyulong.myblog.back.book.service.BookService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 图书表 服务实现类
 * </p>
 *
 * @author lihanlu
 * @since 2020-03-24
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
