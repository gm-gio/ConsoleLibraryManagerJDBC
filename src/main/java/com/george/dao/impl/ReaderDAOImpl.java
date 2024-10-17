package com.george.dao.impl;

import com.george.dao.ReaderDAO;

import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReaderDAOImpl implements ReaderDAO {
    @Override
    public Optional<Reader> findById(Connection connection, Integer id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Reader> findAll(Connection connection) throws SQLException {
        return List.of();
    }

    @Override
    public void update(Connection connection, Reader entity) throws SQLException {

    }

    @Override
    public void deleteById(Connection connection, Integer id) throws SQLException {

    }

    @Override
    public void create(Connection connection, Reader entity) throws SQLException {

    }
}
