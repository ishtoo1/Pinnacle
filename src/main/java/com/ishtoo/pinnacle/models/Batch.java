package com.ishtoo.pinnacle.models;

import java.sql.Date;

public class Batch {
	private String batchId;
	private String batchName;
	private Date startingDate;
	private Date endDate;
	private boolean isOpen;
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public Date getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", batchName=" + batchName + ", startingDate=" + startingDate
				+ ", endDate=" + endDate + ", isOpen=" + isOpen + "]";
	}
	
}
