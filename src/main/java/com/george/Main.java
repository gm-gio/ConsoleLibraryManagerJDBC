package com.george;

import com.george.menu.impl.MenuManagerImpl;
import com.george.console.impl.DefaultConsole;
import com.george.dao.AuthorDAO;
import com.george.dao.BookDAO;
import com.george.dao.OrderDAO;
import com.george.dao.ReaderDAO;
import com.george.dao.impl.AuthorDAOImpl;
import com.george.dao.impl.BookDAOImpl;
import com.george.dao.impl.OrderDAOImpl;
import com.george.dao.impl.ReaderDAOImpl;
import com.george.db.DBManager;
import com.george.db.utils.ResourceFileUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class Main {

    public Main(DBManager dbManager1) throws SQLException, IOException {
    }


    public static void main(String[] args) throws IOException, SQLException {

        Properties properties = ResourceFileUtils.readResourceFileAsProperties("/connection.properties");
        DBManager dbManager = new DBManager(properties);


        AuthorDAO authorDAO = new AuthorDAOImpl();
        BookDAO bookDAO = new BookDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        ReaderDAO readerDAO = new ReaderDAOImpl();

        new DefaultConsole();
        MenuManagerImpl menuManager = new MenuManagerImpl(authorDAO, bookDAO, orderDAO, readerDAO, dbManager);
        Main consoleApp = new Main(dbManager);


        try (Connection connection = dbManager.getConnection()) {
            if (connection != null) {
                System.out.println("Connection to the database established successfully!");

//                SQLUtils.executeScriptFromFile(connection, "/db_migration/V1_Initial_schema.sql");
//                SQLUtils.executeScriptFromFile(connection, "/db_migration/V2_Insert_data.sql");

                menuManager.Start();

            } else {
                System.out.println("Failed to establish a connection to the database.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception occurred: " + e.getMessage());
        }
    }
}