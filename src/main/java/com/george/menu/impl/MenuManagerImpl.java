package com.george.menu.impl;

import com.george.menu.MenuManager;
import com.george.console.Console;
import com.george.console.impl.DefaultConsole;
import com.george.dao.AuthorDAO;
import com.george.dao.BookDAO;
import com.george.dao.OrderDAO;
import com.george.dao.ReaderDAO;
import com.george.db.DBManager;
import com.george.db.utils.SQLUtils;
import com.george.entity.Author;

import java.sql.SQLException;

public class MenuManagerImpl implements MenuManager {
    private final AuthorDAO authorDAO;
    private final BookDAO bookDAO;
    private final OrderDAO orderDAO;
    private final ReaderDAO readerDAO;
    private final DBManager dbManager;

    public MenuManagerImpl(AuthorDAO authorDAO, BookDAO bookDAO, OrderDAO orderDAO, ReaderDAO readerDAO, DBManager dbManager) {
        this.authorDAO = authorDAO;
        this.bookDAO = bookDAO;
        this.orderDAO = orderDAO;
        this.readerDAO = readerDAO;
        this.dbManager = dbManager;
    }

    public void Start () throws SQLException{
        Console console =  new DefaultConsole();
        boolean exit = false;

        while (!exit) {
            console.println("Enter your choice (0 to exit): ");
            printMenu(console);
            int choice = console.nextInt();
            console.nextLine();
            switch (choice) {
                case 1:
                    addAuthor(console);
                    break;
                case 2:
                    removeAuthor(console);
                    break;
                case 3:
                    exit = true;
                    console.println("Exit");
                    break;
                default:
                    console.println("Invalid choice. please try again.");
            }
        }

    }



    public void printMenu(Console console) {
        console.println("1. Insert Author");
        console.println("2. Remove Author");
        console.println("3. Exit");
    }

    private void addAuthor(Console console) {
        console.println("Enter the name of the Author: ");
        String authorName = console.nextLine();

        console.println("Enter address of the Author: ");
        String address = console.nextLine();


        SQLUtils.inTransaction(this.dbManager, (connection) -> {
            Author author = new Author();
            author.setAuthorName(authorName);
            author.setAuthorAddress(address);

            this.authorDAO.create(connection, author);
            console.println("Author added successfully.");
        });
    }

    private void removeAuthor(Console console) {
    }



}
