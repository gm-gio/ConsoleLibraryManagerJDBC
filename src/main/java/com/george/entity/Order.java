package com.george.entity;

import java.util.List;
import java.util.Objects;

public class Order {

    private Integer orderId;
    private String orderDate;
    private Reader reader;
    private List<Book> books;

    public Order(Integer orderId, String orderData,  Reader reader, List<Book> books) {
        this.orderId = orderId;
        this.orderDate = orderData;
        this.reader = reader;
        this.books = books;
    }

    public Order() {

    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }



    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
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
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(orderDate, order.orderDate) && Objects.equals(reader, order.reader) && Objects.equals(books, order.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderDate, reader, books);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderData='" + orderDate + '\'' +
                ", reader=" + reader +
                ", books=" + books +
                '}';
    }
}
