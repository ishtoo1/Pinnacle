package com.ishtoo.pinnacle.models;

public class BatchTestRelation {
	private String batchId;
	private int testId;
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	@Override
	public String toString() {
		return "BatchTestRelation [batchId=" + batchId + ", testId=" + testId + "]";
	}
	
}
