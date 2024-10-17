package com.george.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T, K> {
    Optional<T> findById(Connection connection, K id) throws SQLException;

    List<T> findAll(Connection connection) throws SQLException;

    void update(Connection connection, T entity) throws SQLException;

    void deleteById(Connection connection, K id) throws SQLException;

    void create(Connection connection, T entity) throws SQLException;
}
