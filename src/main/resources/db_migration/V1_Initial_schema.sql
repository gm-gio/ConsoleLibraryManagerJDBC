CREATE SCHEMA IF NOT EXISTS library_manage;

CREATE TABLE IF NOT EXISTS library_manage.authors
(
    author_id      INT AUTO_INCREMENT PRIMARY KEY,
    author_name    VARCHAR(255) NOT NULL,
    author_address VARCHAR(255)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS library_manage.books
(
    book_id   INT AUTO_INCREMENT PRIMARY KEY,
    book_name VARCHAR(255) NOT NULL,
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES library_manage.authors (author_id) ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS library_manage.readers
(
    reader_id    INT AUTO_INCREMENT PRIMARY KEY,
    reader_name  VARCHAR(255) NOT NULL,
    reader_email VARCHAR(255)
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS library_manage.orders
(
    order_id   INT AUTO_INCREMENT PRIMARY KEY,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    reader_id  INT,
    FOREIGN KEY (reader_id) REFERENCES library_manage.readers (reader_id) ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS library_manage.book_order
(
    book_id  INT,
    order_id INT,
    PRIMARY KEY (book_id, order_id),
    FOREIGN KEY (book_id) REFERENCES library_manage.books (book_id) ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES library_manage.orders (order_id) ON DELETE CASCADE

) ENGINE = InnoDB;
