package com.ishtoo.pinnacle.dao;

import java.util.List;

import com.ishtoo.pinnacle.models.Book;

public interface BookDao {
	void addBook(Book book);

	List<Book> findAllAvailableBooks();

//	void issueBook(Book book, int StudentId);
//	void deleteBook(String bookId);
//	List<Book> findAllBooks();
//	List<Book> findAllBooksIssuedToStudent(String username);
//	void returnBook(String bookId);
	List<Book> findAllBooks();

	void deleteBook(String bookId);

	List<Book> findAllBooksIssuedToStudent(int StudentId);
}
