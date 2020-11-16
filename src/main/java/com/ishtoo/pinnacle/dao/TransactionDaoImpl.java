package com.ishtoo.pinnacle.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ishtoo.pinnacle.models.Transaction;

@Repository
public class TransactionDaoImpl implements TransactionDao {

	@Autowired
	JdbcTemplate template;
	
	@Override
	public void addTransaction(Transaction transaction) {
		String sql="insert into Transaction(transactionId, transactionAmount, status, customerId) values(?, ?, ?, ?)";
		template.update(sql, transaction.getTransactionId(), transaction.getTransactionAmount(), transaction.getStatus(), transaction.getCustomerId());
	}
	
	@Override
	public List<Transaction> getAllTransactions() {
		String sql="select * from Transaction";
		return template.query(sql, new BeanPropertyRowMapper<> (Transaction.class));
	}

}
