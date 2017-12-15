package com.dhc.ttshop.lucene.dao;

import com.dhc.ttshop.lucene.pojo.Book;

import java.util.List;

/**
 * User: DHC
 * Date: 2017/12/5
 * Time: 9:50
 * Version:V1.0
 */
public interface IBookDao {
    List<Book> listBooks();
}
