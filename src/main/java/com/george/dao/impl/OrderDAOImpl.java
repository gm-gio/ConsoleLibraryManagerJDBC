package com.george.dao.impl;

import com.george.dao.OrderDAO;
import com.george.entity.Order;
import com.george.mapper.UniversalMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderDAOImpl implements OrderDAO {

    private final UniversalMapper mapper;
    private final static String SELECT_QUERY = "SELECT * FROM library_manage.orders WHERE order_id = ?";

    public OrderDAOImpl(UniversalMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public Optional<Order> findById(Connection connection, Integer orderId) throws SQLException {
        Order order = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_QUERY)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            order = mapper.orderMap(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(order);
    }

    @Override
    public List<Order> findAll(Connection connection) throws SQLException {
        return List.of();
    }

    @Override
    public void update(Connection connection, Order entity) throws SQLException {

    }

    @Override
    public void deleteById(Connection connection, Integer id) throws SQLException {

    }

    @Override
    public void create(Connection connection, Order entity) throws SQLException {

    }
}
