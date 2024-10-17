package com.george.dao.impl;

import com.george.dao.BookDAO;
import com.george.entity.Book;
import com.george.mapper.UniversalMapper;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class BookDAOImpl implements BookDAO {

    private final UniversalMapper mapper;

    public BookDAOImpl(UniversalMapper mapper) {
        this.mapper = mapper;
    }

    private final static String INSERT_QUERY_BOOK_ORDER = "INSERT INTO library_manage.book_order (book_id, order_id) VALUES (?, ?)";
    private final static String DELETE_QUERY_BOOK_ORDER = "DELETE FROM library_manage.book_order WHERE book_id = ? AND order_id = ?";
    private final static String SELECT_QUERY = "SELECT * FROM library_manage.books WHERE book_id = ?";


    @Override
    public Optional<Book> findById(Connection connection, Integer bookId) throws SQLException {
        Book book = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, bookId);
            ResultSet rs = statement.executeQuery();
            rs.next();

            book = mapper.bookMap(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(book);
    }

    @Override
    public void addBookToOrder(Connection connection, int bookId, int orderId) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY_BOOK_ORDER)) {
            statement.setInt(1, bookId);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBookFromOrder(Connection connection, int bookId, int orderId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY_BOOK_ORDER)) {
            statement.setInt(1, bookId);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Book> findAll(Connection connection) throws SQLException {
        return List.of();
    }

    @Override
    public void update(Connection connection, Book entity) throws SQLException {

    }

    @Override
    public void deleteById(Connection connection, Integer id) throws SQLException {

    }

    @Override
    public void create(Connection connection, Book entity) throws SQLException {

    }
}
