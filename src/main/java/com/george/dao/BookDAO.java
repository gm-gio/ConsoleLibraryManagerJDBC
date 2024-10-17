package com.george.dao;

import com.george.entity.Book;

import java.sql.Connection;
import java.util.Optional;

public interface BookDAO extends CrudDAO<Book, Integer> {

    void addBookToOrder(Connection connection, int bookId, int orderId);

    void deleteBookFromOrder(Connection connection, int bookId, int orderId);
}
