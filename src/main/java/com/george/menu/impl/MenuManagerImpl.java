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
import java.util.List;

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

    public void Start() throws SQLException {
        Console console = new DefaultConsole();
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
                    updateAuthor(console);
                    break;
                case 4:
                    findAuthors(console);
                    break;
                case 5:
                    addBookToOrder(console);
                    break;
                case 6:
                    removeBookFromOrder(console);
                    break;
                case 7:
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
        console.println("3. Update Author");
        console.println("4. Find all Authors");
        console.println("5. Add Book to Order ");
        console.println("6. Remove Book from Order");
        console.println("0. Exit");
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
        console.println("Enter the Authors ID to remove: ");
        Integer authorId = console.nextInt();

        SQLUtils.inTransaction(this.dbManager, (connection) -> {
            authorDAO.deleteById(connection, authorId);
            console.println("Author with ID" + authorId + " removed successfully.");
        });
    }

    private void updateAuthor(Console console) {
        console.println("Enter the Authors ID to update: ");
        Integer authorId = console.nextInt();
        console.nextLine();

        try {
            var authorOptional = authorDAO.findById(dbManager.getConnection(), authorId);

            if (authorOptional.isPresent()) {
                console.println("Enter the new name of the Author: ");
                String newName = console.nextLine();
                console.println("Enter the new address of the Author: ");
                String newAddress = console.nextLine();

                SQLUtils.inTransaction(this.dbManager, (connection) -> {
                    Author author = authorOptional.get();
                    author.setAuthorName(newName);
                    author.setAuthorAddress(newAddress);
                    this.authorDAO.update(connection, author);
                    console.println("Author updated successfully.");
                });
            } else {
                console.println("No author found with ID: " + authorId);
            }
        } catch (SQLException e) {
            console.println("Error while updating author: " + e.getMessage());
        }
    }


    private void findAuthors(Console console) {
        SQLUtils.inTransaction(this.dbManager, (connection) -> {
            List<Author> authors = authorDAO.findAll(connection);

            if (authors.isEmpty()) {
                console.println("No authors found.");
            } else {
                console.println("Found " + authors.size() + " authors.");
                for (Author author : authors) {
                    console.println(author.toString());
                }
            }
        });
    }

    private void removeBookFromOrder(Console console) {
        console.println("Enter the Book ID to remove: ");
        int bookId = console.nextInt();

        console.println("Enter the Order ID : ");
        int orderId = console.nextInt();

        SQLUtils.inTransaction(this.dbManager, (connection) -> {
            bookDAO.deleteBookFromOrder(connection, bookId, orderId);
            console.println("Book with ID" + bookId + " removed successfully.");
        });
    }

    private void addBookToOrder(Console console) {
        console.println("Enter the Book ID to add: ");
        int bookId = console.nextInt();
        console.println("Enter the Order ID : ");
        int orderId = console.nextInt();
        SQLUtils.inTransaction(this.dbManager, (connection) -> {
            bookDAO.addBookToOrder(connection, bookId, orderId);
            console.println("Book with ID" + bookId + " added successfully.");
        });
    }


}
