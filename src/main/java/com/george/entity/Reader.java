package com.george.entity;

import java.util.List;
import java.util.Objects;

public class Reader {

    private Integer readerId;
    private String readerName;
    private String readerEmail;
    private List<Order> orders;

    public Reader(Integer readerId, String readerName, String readerEmail, List<Order> orders) {
        this.readerId = readerId;
        this.readerName = readerName;
        this.readerEmail = readerEmail;
        this.orders = orders;
    }

    public Reader() {

    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getReaderEmail() {
        return readerEmail;
    }

    public void setReaderEmail(String readerEmail) {
        this.readerEmail = readerEmail;
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
        Reader reader = (Reader) o;
        return Objects.equals(readerId, reader.readerId) && Objects.equals(readerName, reader.readerName) && Objects.equals(readerEmail, reader.readerEmail) && Objects.equals(orders, reader.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readerId, readerName, readerEmail, orders);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "readerId=" + readerId +
                ", readerName='" + readerName + '\'' +
                ", readerEmail='" + readerEmail + '\'' +
                ", orders=" + orders +
                '}';
    }
}
