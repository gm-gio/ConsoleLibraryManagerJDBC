package com.george.entity;

import java.util.List;
import java.util.Objects;

public class Book {

    private Integer bookId;
    private String bookName;
    private Author author;
    private List<Order> orders;

    public Book(Integer bookId, String bookName, Author author, List<Order> orders) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.orders = orders;
    }

    public Book() {

    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId) && Objects.equals(bookName, book.bookName) && Objects.equals(author, book.author) && Objects.equals(orders, book.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookName, author, orders);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", author=" + author +
                ", orders=" + orders +
                '}';
    }
}
