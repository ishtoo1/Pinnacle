package com.ishtoo.pinnacle.dao;

import java.util.List;

import com.ishtoo.pinnacle.models.Transaction;

public interface TransactionDao {

	void addTransaction(Transaction transaction);

	List<Transaction> getAllTransactions();

}
