package com.ishtoo.pinnacle.dao;

import java.util.List;

import com.ishtoo.pinnacle.models.Book;
import com.ishtoo.pinnacle.models.BookIssueTemp;
import com.ishtoo.pinnacle.models.Student;

public interface StudentBookRelationDao {

	void issueBook(Book book, int studentId);

	void returnBook(String bookId);

	Student findOwnerOfBook(int bookId);

	List<BookIssueTemp> findBooksIssuedToStudent(int studentId);

}
