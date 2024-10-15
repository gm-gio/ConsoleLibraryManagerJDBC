package com.george.mapper;

import com.george.entity.Author;
import com.george.entity.Book;
import com.george.entity.Order;
import com.george.entity.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface UniversalMapper {
    Author authorMap(ResultSet resultSet) throws SQLException;

    Book bookMap(ResultSet resultSet) throws SQLException;

    Order orderMap(ResultSet resultSet) throws SQLException;

    Reader readerMap(ResultSet resultSet) throws SQLException;
}
