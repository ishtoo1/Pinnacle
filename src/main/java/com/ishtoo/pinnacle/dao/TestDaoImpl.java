package com.ishtoo.pinnacle.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ishtoo.pinnacle.models.Test;

@Repository
public class TestDaoImpl implements TestDao {

	@Autowired
	JdbcTemplate template;

	@Override
	public void addTest(Test test) {
		String sql = "insert into Test(testName, testDate, totalMarks) values(?, ?, ?)";
		template.update(sql, test.getTestName(), test.getTestDate(), test.getTotalMarks());
	}

	@Override
	public List<Test> findAllUpcomingTests() {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		String sql = "select * from Test where testDate >= ?";
		return template.query(sql, new Object[] { date }, new BeanPropertyRowMapper<>(Test.class));
	}

	@Override
	public List<Test> findAllTests() {
		String sql = "select * from Test";
		return template.query(sql, new BeanPropertyRowMapper<>(Test.class));
	}

	@Override
	public void deleteTest(String testId) {
		String sql = "delete from Test where testId=?";
		template.update(sql, testId);
	}

	@Override
	public Test findById(int testId) {
		String sql = "select * from Test where testId=?";
		try {
			return (Test) template.queryForObject(sql, new Object[] { testId },
					new BeanPropertyRowMapper<>(Test.class));
		} catch (Exception e) {
			return null;
		}
	}
}
