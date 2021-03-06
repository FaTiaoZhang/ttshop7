package com.dhc.ttshop.lucene.dao.impl;

import com.dhc.ttshop.lucene.dao.IBookDao;
import com.dhc.ttshop.lucene.pojo.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * User: DHC
 * Date: 2017/12/5
 * Time: 9:51
 * Version:V1.0
 */
public class BookDaoImpl implements IBookDao {
    @Override
    public List<Book> listBooks() {
        //数据库连接
        Connection connection = null;
        //预编译语句对象
        PreparedStatement preparedStatement = null;
        //结果集对象
        ResultSet resultSet = null;
        //图书列表
        List<Book> list = new ArrayList<Book>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lucene", "root", "dhcdhc");
            preparedStatement = connection.prepareStatement("select * from book");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setPrice(resultSet.getFloat("price"));
                book.setPic(resultSet.getString("pic"));
                book.setDescription(resultSet.getString("description"));
                list.add(book);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
