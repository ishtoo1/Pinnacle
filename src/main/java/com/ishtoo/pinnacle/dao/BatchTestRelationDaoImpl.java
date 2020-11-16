package com.ishtoo.pinnacle.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ishtoo.pinnacle.models.Batch;
import com.ishtoo.pinnacle.models.BatchTestRelation;
import com.ishtoo.pinnacle.models.Test;

@Repository
public class BatchTestRelationDaoImpl implements BatchTestRelationDao {

	@Autowired
	JdbcTemplate template;

	@Override
	public boolean checkExistence(BatchTestRelation batchTestRelation) {
		String sql = "select exists (select * from BatchTestRelation where batchId=? and testId=?)";
		return template.queryForObject(sql, Boolean.class,
				new Object[] { batchTestRelation.getBatchId(), batchTestRelation.getTestId() });
	}

	@Override
	public void addTestToBatch(BatchTestRelation batchTestRelation) {
		String sql = "insert into BatchTestRelation(batchId, testId) values(?, ?)";
		template.update(sql, batchTestRelation.getBatchId(), batchTestRelation.getTestId());
	}

	@Override
	public List<Test> findUpcomingTestsNotInThisBatch(String batchId) {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		String sql = "select distinct test.testId, testName, testDate, totalMarks "
				+ "from (select * from BatchTestRelation where batchId=?) as A "
				+ "right join test on A.testId=test.testId " + "where A.batchId is null and testDate>=? ";
		return template.query(sql, new Object[] { batchId, date }, new BeanPropertyRowMapper<>(Test.class));
	}

	@Override
	public List<Test> findTestsInThisBatch(String batchId) {
		String sql = "select distinct test.testId, testName, testDate, totalMarks "
				+ "from (select * from BatchTestRelation where batchId=?) as A "
				+ "right join test on A.testId=test.testId " + "where A.batchId is not null";
		return template.query(sql, new Object[] { batchId }, new BeanPropertyRowMapper<>(Test.class));
	}

	@Override
	public void deleteTestFromBatch(String batchId, String testId) {
		String sql = "delete from BatchTestRelation where batchId=? and testId=?";
		template.update(sql, batchId, testId);
	}

	@Override
	public List<Batch> findBatchesInTest(int testId) {
		String sql = "select distinct batch.batchId, batchName, startingDate, endDate, isOpen "
				+ "from (select * from BatchTestRelation where testId=?) as A "
				+ "right join batch on A.batchId=batch.batchId " + "where A.testId is not null";
		return template.query(sql, new Object[] { testId }, new BeanPropertyRowMapper<>(Batch.class));
	}

	@Override
	public List<Test> findUpcomingTestsInThisBatch(String batchId) {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		String sql = "select distinct test.testId, testName, testDate, totalMarks "
				+ "from (select * from BatchTestRelation where batchId=?) as A "
				+ "right join test on A.testId=test.testId " + "where A.batchId is not null and testDate>=?";
		return template.query(sql, new Object[] { batchId, date }, new BeanPropertyRowMapper<>(Test.class));
	}
}
