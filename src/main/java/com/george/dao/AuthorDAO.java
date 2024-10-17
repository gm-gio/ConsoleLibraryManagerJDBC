package com.george.dao;

import com.george.entity.Author;

import java.sql.Connection;


public interface AuthorDAO extends CrudDAO<Author, Integer> {

    void create(Connection connection, Author author);
}
