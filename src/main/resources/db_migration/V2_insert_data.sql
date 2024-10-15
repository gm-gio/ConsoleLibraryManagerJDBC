INSERT INTO library_manage.authors (author_id, author_name, author_address)
VALUES (1000000, 'John Smith', '123 Elm St, Springfield, IL'),
       (2000000, 'Emily Johnson', '456 Oak St, Lincoln, NE'),
       (3000000, 'Michael Brown', '789 Pine St, Madison, WI'),
       (4000000, 'Sarah Davis', '101 Maple St, Omaha, NE'),
       (5000000, 'David Wilson', '202 Birch St, Chicago, IL'),
       (6000000, 'Laura Garcia', '303 Cedar St, Denver, CO'),
       (7000000, 'James Martinez', '404 Walnut St, Seattle, WA'),
       (8000000, 'Maria Rodriguez', '505 Spruce St, Portland, OR'),
       (9000000, 'Daniel Lee', '606 Ash St, Austin, TX'),
       (10000000, 'Sophia Harris', '707 Cherry St, San Francisco, CA');

INSERT INTO library_manage.books (book_id, book_name, author_id)
VALUES (10000, 'The Great Adventure', 1000000),
       (10001, 'A Day in the Life', 2000000),
       (10002, 'Understanding the Universe', 3000000),
       (10003, 'Cooking for Beginners', 4000000),
       (10004, 'Journey Through Time', 5000000),
       (10005, 'Mystery of the Lost Treasure', 6000000),
       (10006, 'Science Fiction 101', 7000000),
       (10007, 'The Art of Painting', 8000000),
       (10008, 'Traveling the World', 9000000),
       (10009, 'Fitness and Health', 10000000),
       (10010, 'History of Civilization', 1000000),
       (10011, 'Gardening Basics', 2000000),
       (10012, 'Mastering Python', 3000000),
       (10013, 'Photography for Everyone', 4000000),
       (10014, 'Building Your Future', 5000000),
       (10015, 'Understanding Economics', 6000000),
       (10016, 'The Secret of Success', 7000000),
       (10017, 'Yoga for Beginners', 8000000),
       (10018, 'The World of Robotics', 9000000),
       (10019, 'Sustainable Living', 10000000);

INSERT INTO library_manage.readers (reader_id, reader_name, reader_email)
VALUES (100000, 'Alice Thompson', 'alice.thompson@example.com'),
       (200000, 'Bob Smith', 'bob.smith@example.com'),
       (300000, 'Catherine Johnson', 'catherine.johnson@example.com'),
       (400000, 'David Brown', 'david.brown@example.com'),
       (500000, 'Emma Davis', 'emma.davis@example.com'),
       (600000, 'Frank Wilson', 'frank.wilson@example.com'),
       (700000, 'Grace Lee', 'grace.lee@example.com'),
       (800000, 'Henry Martinez', 'henry.martinez@example.com'),
       (900000, 'Ivy Rodriguez', 'ivy.rodriguez@example.com'),
       (1000000, 'Jack White', 'jack.white@example.com');

INSERT INTO library_manage.orders (order_id, order_date, reader_id)
VALUES (100000, '2024-10-01 10:00:00', 100000),
       (200000, '2024-10-02 11:00:00', 200000),
       (300000, '2024-10-03 12:00:00', 300000),
       (400000, '2024-10-04 13:00:00', 400000),
       (500000, '2024-10-05 14:00:00', 500000);

INSERT INTO library_manage.book_order (order_id, book_id)
VALUES (100000, 1000000),
       (100000, 2000000),
       (200000, 3000000),
       (200000, 4000000),
       (300000, 5000000),
       (300000, 6000000),
       (400000, 7000000),
       (400000, 8000000),
       (500000, 9000000),
       (500000, 10000000);

