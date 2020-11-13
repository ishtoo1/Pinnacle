package com.ishtoo.pinnacle.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ishtoo.pinnacle.models.Book;

@Repository
public class BookDaoImpl implements BookDao {
	
	@Autowired
	JdbcTemplate template;
	
	@Autowired
	StudentDao studentDao;
	
	@Override
	public void addBook(Book book) {
		String sql="insert into Book(bookName) values(?)";
		template.update(sql, book.getBookName());
	}
	
	@Override
	public List<Book> findAllAvailableBooks() {
		String sql="select book.bookId, bookName from StudentBookRelation as A right join Book on A.bookId=Book.bookId where A.studentId is null";
		return template.query(sql, new BeanPropertyRowMapper<>(Book.class));
	}

	@Override
	public List<Book> findAllBooks() {
		String sql="select * from Book";
		return template.query(sql, new BeanPropertyRowMapper<>(Book.class));
	}

	@Override
	public void deleteBook(String bookId) {
		String sql="delete from Book where bookId=?";
		template.update(sql, bookId);
	}

	@Override
	public List<Book> findAllBooksIssuedToStudent(int studentId) {
		String sql="select distinct book.bookId, bookName "
				+ "from (select * from StudentBookRelation where studentId=?) as A "
				+ "right join Book on A.bookId=book.bookId where A.studentId is not null";
		return template.query(sql, new Object[] {studentId}, new BeanPropertyRowMapper<>(Book.class));
	}
	
}