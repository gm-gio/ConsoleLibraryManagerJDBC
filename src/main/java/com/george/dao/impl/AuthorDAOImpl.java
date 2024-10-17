package com.george.dao.impl;

import com.george.dao.AuthorDAO;
import com.george.entity.Author;
import com.george.mapper.UniversalMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


public class AuthorDAOImpl implements AuthorDAO {


    private static final Logger LOGGER = Logger.getLogger(AuthorDAOImpl.class.getName());
    private final UniversalMapper mapper;

    private final static String INSERT_QUERY = "INSERT INTO library_manage.authors (author_name, author_address) VALUES (?,?)";
    private final static String SELECT_QUERY = "SELECT * FROM library_manage.authors WHERE author_id = ?";
    private final static String DELETE_QUERY = "DELETE FROM library_manage.authors WHERE author_id = ?";
    private final static String SELECT_ALL_QUERY = "SELECT * FROM library_manage.authors";
    private final static String UPDATE_QUERY = "UPDATE library_manage.authors SET author_name = ?, author_address = ? WHERE author_id = ?";

    public AuthorDAOImpl(UniversalMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public void create(Connection connection, Author author) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Author> findById(Connection connection, Integer authorId) {
        Author author = null;

        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, authorId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            author = mapper.authorMap(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(author);
    }

    @Override
    public List<Author> findAll(Connection connection) {
        List<Author> authors = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authors.add(mapper.authorMap(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authors;
    }

    @Override
    public void update(Connection connection, Author author) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, author.getAuthorName());
            statement.setString(2, author.getAuthorAddress());
            statement.setInt(3, author.getAuthorId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteById(Connection connection, Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.warning("No author found with id: " + id);
                throw new SQLException("No author found with id: " + id);
            }
        } catch (SQLException e) {
            LOGGER.severe("Error while removing author: " + e.getMessage());
            throw e;
        }
    }
}
