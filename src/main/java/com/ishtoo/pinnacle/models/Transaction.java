package com.ishtoo.pinnacle.models;

public class Transaction {
	private String transactionId;
	private String transactionAmount;
	private String status;
	private String customerId;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionAmount=" + transactionAmount + ", status="
				+ status + ", customerId=" + customerId + "]";
	}
	
	public Transaction(String transactionId, String transactionAmount, String status, String customerId) {
		super();
		this.transactionId = transactionId;
		this.transactionAmount = transactionAmount;
		this.status = status;
		this.customerId = customerId;
	}
	public Transaction() {
		super();
	}
	
}
