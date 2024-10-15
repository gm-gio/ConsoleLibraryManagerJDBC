package com.george.mapper.impl;

import com.george.entity.Author;
import com.george.entity.Book;
import com.george.entity.Order;
import com.george.entity.Reader;
import com.george.mapper.UniversalMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UniversalMapperImpl implements UniversalMapper {

    private String getString(ResultSet resultSet, String columnLabel) throws SQLException {
        return resultSet.getString(columnLabel);
    }

    private int getInt(ResultSet resultSet, String columnLabel) throws SQLException {
        return resultSet.getInt(columnLabel);
    }

    @Override
    public Author authorMap(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setAuthorId(getInt(resultSet, "author_id"));
        author.setAuthorName(getString(resultSet, "author_name"));
        author.setAuthorAddress(getString(resultSet, "author_address"));
        return author;
    }

    @Override
    public Book bookMap(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setBookId(getInt(resultSet, "book_id"));
        book.setBookName(getString(resultSet, "book_name"));
        return book;
    }

    @Override
    public Order orderMap(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setOrderId(getInt(resultSet, "order_id"));
        order.setOrderDate(getString(resultSet, "order_date"));
        return order;
    }

    @Override
    public Reader readerMap(ResultSet resultSet) throws SQLException {
        Reader reader = new Reader();
        reader.setReaderId(getInt(resultSet, "reader_id"));
        reader.setReaderName(getString(resultSet, "reader_name"));
        reader.setReaderEmail(getString(resultSet, "reader_email"));
        return reader;
    }

}
