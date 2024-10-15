package com.george.db.utils;

import com.george.db.DBManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLUtils {

    private final static Logger log = Logger.getLogger(SQLUtils.class.getName());

    private final DBManager dbManager;

    public SQLUtils(DBManager dbManager) {
        this.dbManager = dbManager;
    }


    public static void executeScriptFromFile(Connection connection, String scriptFilePath) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(SQLUtils.class.getResourceAsStream(scriptFilePath))))) {
            StringBuilder sqlBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty() && !line.startsWith("--")) {
                    sqlBuilder.append(line);
                    sqlBuilder.append("\n");
                }
            }

            String[] sqlStatements = sqlBuilder.toString().split(";");
            try (Statement statement = connection.createStatement()) {
                for (String sql : sqlStatements) {
                    if (!sql.trim().isEmpty()) {
                        statement.execute(sql.trim());
                    }
                }
            }

            System.out.println("Script executed successfully: " + scriptFilePath);
        } catch (Exception e) {
            System.err.println("Error executing script: " + e.getMessage());
        }
    }

    public interface UnsafeConsumer<T> {
        void accept(T object) throws Exception;
    }

    public interface UnsafeFunction<T, R> {
        R apply(T object);
    }

    public static void inTransaction(DBManager dbManager, UnsafeConsumer<java.sql.Connection> action) {
        try (java.sql.Connection connection = dbManager.getConnection()) {
            connection.setAutoCommit(false);
            try {
                action.accept(connection);
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static <T> T transaction(DBManager dbManager, UnsafeFunction<java.sql.Connection, T> action) {
        try (java.sql.Connection connection = dbManager.getConnection()) {
            connection.setAutoCommit(false);
            try {
                T result = action.apply(connection);
                connection.commit();
                return result;
            } catch (Exception e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public ResultSet executeQuery(String sql) throws SQLException {
        try (java.sql.Connection connection = this.dbManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeQuery();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "SQL query execution failed: " + sql, e);
            throw e;
        }
    }

    public void executeUpdate(String sql, Object... values) throws SQLException {
        try (java.sql.Connection connection = this.dbManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            setParameters(statement, values);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error executing SQL update", e);
        }
    }

    private void setParameters(PreparedStatement statement, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }
    }

}
