package com.ishtoo.pinnacle.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ishtoo.pinnacle.models.Subject;

@Repository
public class SubjectDaoImpl implements SubjectDao {
	
	@Autowired
	JdbcTemplate template;

	@Override
	public boolean checkIfSubjectExistsInBatch(String subjectId, String batchId) {
		String sql="select exists (select * from Subject where subjectId=? and batchId=?)";
		return template.queryForObject(sql, Boolean.class, new Object[] {subjectId, batchId});
	}

	@Override
	public void addSubject(Subject subject) {
		String sql="insert into Subject(subjectId, subjectName, batchId, teacherId) values(?, ?, ?, ?)";
		template.update(sql, subject.getSubjectId(), subject.getSubjectName(), subject.getBatchId(), subject.getTeacherId());
	}

	@Override
	public List<Subject> findAllSubjects() {
		String sql="select * from Subject";
		return template.query(sql, new BeanPropertyRowMapper<>(Subject.class));
	}

	@Override
	public void changeSubjectTeacher(Subject subject) {
		String sql="update Subject set teacherId=? where subjectId=? and batchId=?";
		template.update(sql, subject.getTeacherId(), subject.getSubjectId(), subject.getBatchId());
	}

	@Override
	public List<Subject> findSubjectsInThisBatch(String batchId) {
		String sql="select * from Subject where batchId=?";
		return template.query(sql, new Object[] {batchId}, new BeanPropertyRowMapper<>(Subject.class));
	}

	@Override
	public void deleteSubject(String batchId, String subjectId) {
		String sql="delete from Subject where batchId=? and subjectId=?";
		template.update(sql, batchId, subjectId);
	}

	@Override
	public Subject findSubjectById(String batchId, String subjectId) {
		String sql="select * from Subject where batchId=? and subjectId=?";
		try {
			return (Subject)template.queryForObject(sql, new Object[] {batchId, subjectId}, new BeanPropertyRowMapper<>(Subject.class));
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Subject> findSubjectsTaughtByTeacher(int teacherId) {
		String sql="select * from Subject where teacherId=?";
		return template.query(sql, new Object[] {teacherId}, new BeanPropertyRowMapper<>(Subject.class));
	}

	@Override
	public List<Subject> findSubjectsInThisBatchTaughtByTeacher(String batchId, int teacherId) {
		String sql="select * from Subject where teacherId=? and batchId=?";
		return template.query(sql, new Object[] {teacherId, batchId}, new BeanPropertyRowMapper<>(Subject.class));
	}
	
	
}
