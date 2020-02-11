CREATE DATABASE BooksDB;

USE BooksDB;
CREATE TABLE books( 
     book_id INT UNIQUE NOT NULL auto_increment,
     book_name VARCHAR(50),
     date_published DATETIME,
	 no_pages INT,
     PRIMARY KEY (book_id) 
     );
     
CREATE TABLE authors(
	author_id INT UNIQUE NOT NULL auto_increment,
    author_name VARCHAR(50),
    birth_date DATETIME,
    PRIMARY KEY (author_id)
    );
    
CREATE TABLE books_authors(
	id INT UNIQUE NOT NULL auto_increment ,
    book_id INT NOT NULL,
    author_id INT NOT NULL,
    FOREIGN KEY(book_id) references books(book_id) ON DELETE CASCADE,
    FOREIGN KEY(author_id) references authors(author_id) ON DELETE CASCADE,
	PRIMARY KEY (id)
     );
     
     
INSERT INTO books VALUES ( 5, "cartea1",  '20120618', 123);   

ALTER TABLE books MODIFY date_published DATE;
ALTER TABLE authors MODIFY birth_date DATE;

INSERT INTO books(book_name,date_published,no_pages) VALUES ("cartea2","20001209",500);

INSERT INTO authors(author_name, birth_date) VALUES("autorul1", '19450303');
INSERT INTO authors(author_name, birth_date) VALUES("autorul2", '19780612');

INSERT INTO books_authors(book_id,author_id) VALUES (5,1);
INSERT INTO books_authors(book_id,author_id) VALUES (5,2);
INSERT INTO books_authors(book_id,author_id) VALUES (6,2);

SELECT * FROM books_authors WHERE book_id=5;

SELECT author_name FROM authors WHERE author_name LIKE '%rul2'






    
    