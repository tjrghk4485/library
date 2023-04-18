insert into library (library_id, name, opening_date, closed_date, created_date)
values
    (1000, '도서관1', '09:00', '18:00', now()),
    (1001, '도서관2', '10:00', '20:00', now()),
    (1002, '도서관3', '09:00', '24:00', now());

insert into user_info(user_id, name, age, gender, phone, library_id)
values (1000, 'user1', 33, 'MALE', '01012341234', 1000);

insert into book(book_id, title,author, category, publisher)
values
    (1000, 'book1', 'author1', 'NOVEL', 'publisher1'),
    (1001, 'book2', 'author2', 'SCIENCE', 'publisher2'),
    (1002, 'book3', 'author3', 'ART', 'publisher3'),
    (1003, 'book4', 'author4', 'NOVEL', 'publisher4'),
    (1004, 'book5', 'author5', 'SCIENCE', 'publisher5'),
    (1005, 'book6', 'author6', 'NOVEL', 'publisher6'),
    (1006, 'book7', 'author7', 'ART', 'publisher7'),
    (1007, 'book8', 'author8', 'NOVEL', 'publisher8'),
    (1008, 'book9', 'author9', 'ART', 'publisher9'),
    (1009, 'book10', 'author10', 'IT', 'publisher10'),
    (1010, 'book11', 'author11', 'IT', 'publisher11'),
    (1011, 'book12', 'author12', 'ART', 'publisher12');

insert into user_loan_history(history_id, status, user_id, book_id, library_id, loan_date, returned_date)
values
    (1000, 'RETURNED', 1000, 1000, 1000, '2022-06-04', '2022-06-10'),
    (1001, 'LOANED', 1000, 1001, 1000, '2022-09-01', null),
    (1002, 'RETURNED', 1000, 1002, 1000, '2022-06-04', '2022-06-10');