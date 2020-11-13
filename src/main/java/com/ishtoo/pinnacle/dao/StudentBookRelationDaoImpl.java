package com.ishtoo.pinnacle.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ishtoo.pinnacle.models.Book;
import com.ishtoo.pinnacle.models.BookIssueTemp;
import com.ishtoo.pinnacle.models.Student;

@Repository
public class StudentBookRelationDaoImpl implements StudentBookRelationDao {
	
	@Autowired
	JdbcTemplate template;
	
	@Override
	public void issueBook(Book book, int studentId) {
		Date issueDate = new Date(Calendar.getInstance().getTime().getTime());
		Date returnDueDate=Date.valueOf(issueDate.toLocalDate().plusDays(14));
		String sql="insert into StudentBookRelation(issueDate, returnDueDate, studentId, bookId) values(?, ?, ?, ?)";
		template.update(sql, issueDate, returnDueDate, studentId, book.getBookId());
	}

	@Override
	public void returnBook(String bookId) {
		String sql="delete from StudentBookRelation where bookId=?";
		template.update(sql, bookId);
	}

	@Override
	public Student findOwnerOfBook(int bookId) {
		String sql="select distinct student.studentId, studentName, dateOfBirth, gender, address, contactNumber, batchId, username "
				+ "from (select * from StudentBookRelation where bookId=?) as A "
				+ "right join student on A.studentId=student.studentId "
				+ "where A.bookId is not null";
		try {
			return (Student)template.queryForObject(sql, new Object[] {bookId}, new BeanPropertyRowMapper<>(Student.class));
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<BookIssueTemp> findBooksIssuedToStudent(int studentId) {
		String sql="select distinct book.bookId, bookName, A.issueDate, A.returnDueDate "
				+ "from (select * from StudentBookRelation where studentId=?) as A "
				+ "right join book on A.bookId=book.bookId "
				+ "where A.studentId is not null";
		return template.query(sql, new Object[] {studentId}, new BeanPropertyRowMapper<>(BookIssueTemp.class));
	}
}
