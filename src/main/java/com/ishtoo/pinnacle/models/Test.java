package com.ishtoo.pinnacle.models;

import java.sql.Date;

public class Test {
	private int testId;
	private String testName;
	private Date testDate;
	private int totalMarks;
	
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public int getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	@Override
	public String toString() {
		return "Test [testId=" + testId + ", testName=" + testName + ", testDate=" + testDate + ", totalMarks="
				+ totalMarks + "]";
	}
	
}
