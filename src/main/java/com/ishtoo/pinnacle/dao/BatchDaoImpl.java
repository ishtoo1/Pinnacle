package com.ishtoo.pinnacle.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ishtoo.pinnacle.models.Batch;

@Repository
public class BatchDaoImpl implements BatchDao {

	@Autowired
	JdbcTemplate template;

	@Override
	public List<Batch> findAllOpenBatches() {
		String sql = "select * from Batch where isOpen = 1";
		return template.query(sql, new BeanPropertyRowMapper<>(Batch.class));
	}

	@Override
	public List<Batch> findAllClosedBatches() {
		String sql = "select * from Batch where isOpen = 0";
		return template.query(sql, new BeanPropertyRowMapper<>(Batch.class));
	}

	@Override
	public List<Batch> findAllBatches() {
		String sql = "select * from Batch";
		return template.query(sql, new BeanPropertyRowMapper<>(Batch.class));
	}

	@Override
	public void addBatch(Batch batch) {
		String sql = "insert into Batch(batchId, batchName, startingDate, endDate, isOpen, fees) values(?, ?, ?, ?, ?, ?)";
		template.update(sql, batch.getBatchId(), batch.getBatchName(), batch.getStartingDate(), batch.getEndDate(),
				true, batch.getFees());
	}

	@Override
	public void openAdmissionsForBatch(Batch batch) {
		String sql = "update Batch set isOpen=? where batchId=?";
		template.update(sql, true, batch.getBatchId());
	}

	@Override
	public void closeAdmissionsForBatch(Batch batch) {
		String sql = "update Batch set isOpen=? where batchId=?";
		template.update(sql, false, batch.getBatchId());
	}

	@Override
	public boolean checkIfBatchAlreadyExists(Batch batch) {
		String sql = "select exists (select * from Batch where batchId=?)";
		return template.queryForObject(sql, Boolean.class, new Object[] { batch.getBatchId() });
	}

	@Override
	public void deleteBatch(String batchId) {
		String sql = "delete from Batch where batchId=?";
		template.update(sql, batchId);
	}

	@Override
	public Batch findBatchById(String batchId) {
		String sql = "select * from Batch where batchId=?";
		try {
			return (Batch) template.queryForObject(sql, new Object[] { batchId },
					new BeanPropertyRowMapper<>(Batch.class));
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Batch> findAllBatchesHavingTeacher(int teacherId) {
		String sql = "select distinct batch.batchId, batchName, startingDate, endDate, isOpen, fees from "
				+ "(select * from Subject where teacherId=?) as A " + "right join Batch on A.batchId=batch.batchId "
				+ "where A.teacherId is not null";
		return template.query(sql, new Object[] { teacherId }, new BeanPropertyRowMapper<>(Batch.class));
	}
}
