package com.george.dao.impl;

import com.george.dao.AuthorDAO;
import com.george.entity.Author;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class AuthorDAOImpl implements AuthorDAO {

    private final static String INSERT_QUERY = "INSERT INTO library_manage.authors (author_name, author_address) VALUES (?,?)";


    @Override
    public Author create(Connection connection, Author author) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, author.getAuthorName());
            ps.setString(2, author.getAuthorAddress());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Integer generatedId = generatedKeys.getInt(1);
                        author.setAuthorId(generatedId);
                    } else {
                        throw new SQLException("Failed to retrieve the generated ID.");
                    }
                }
            } else {
                throw new RuntimeException("Failed to insert Author. No rows affected.");
            }
            return author;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Author> findById(Connection connection, Integer id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Author> findAll(Connection connection) throws SQLException {
        return List.of();
    }

    @Override
    public Author update(Connection connection, Author entity) throws SQLException {
        return null;
    }

    @Override
    public void deleteById(Connection connection, Integer id) throws SQLException {

    }
}
