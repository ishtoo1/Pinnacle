package com.ishtoo.pinnacle.models;

import java.sql.Date;

public class BookIssueTemp {
	private int bookId;
	private String bookName;
	private Date issueDate;
	private Date returnDueDate;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
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
		return "BookIssueTemp [bookId=" + bookId + ", bookName=" + bookName + ", issueDate=" + issueDate
				+ ", returnDueDate=" + returnDueDate + "]";
	}

}
