package com.ishtoo.pinnacle.models;

import java.sql.Date;

public class StudentBookRelation {
	int bookId;
	int studentId;
	Date issueDate;
	Date returnDueDate;
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getReturnDueDate() {
		return returnDueDate;
	}
	public void setReturnDueDate(Date returnDueDate) {
		this.returnDueDate = returnDueDate;
	}
	@Override
	public String toString() {
		return "StudentBookRelation [bookId=" + bookId + ", studentId=" + studentId + ", issueDate=" + issueDate
				+ ", returnDueDate=" + returnDueDate + "]";
	}
	
}
