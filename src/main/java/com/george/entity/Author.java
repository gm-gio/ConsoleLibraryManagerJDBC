package com.george.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Author {

    private Integer authorId;
    private String authorName;
    private String authorAddress;
    private List<Book> books;

    public Author() {
        this.books = new ArrayList<>();
    }

    public Author(Integer authorId, String authorName, String address, List<Book> books) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorAddress = address;
        this.books = books;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorAddress() {
        return authorAddress;
    }

    public void setAuthorAddress(String authorAddress) {
        this.authorAddress = authorAddress;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(authorId, author.authorId) && Objects.equals(authorName, author.authorName) && Objects.equals(authorAddress, author.authorAddress) && Objects.equals(books, author.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, authorName, authorAddress, books);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", address='" + authorAddress + '\'' +
                ", books=" + books +
                '}';
    }
}
