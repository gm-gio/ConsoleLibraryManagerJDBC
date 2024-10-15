package com.george.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

    private static final String PROPERTIES_FILE = "connection.properties";
    private final String jdbcUrl;
    private final String username;
    private final String password;

    public DBManager(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    public DBManager(Properties properties) {
        this(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.jdbcUrl, this.username, this.password);
    }
}
