package com.ishtoo.pinnacle.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ishtoo.pinnacle.models.Lecture;

@Repository
public class LectureDaoImpl implements LectureDao {
	
	@Autowired
	JdbcTemplate template;

	@Override
	public void addLecture(Lecture lecture) {
		String sql="insert into Lecture(lectureName, subjectId, batchId) values(?, ?, ?)";
		template.update(sql, lecture.getLectureName(), lecture.getSubjectId(), lecture.getBatchId());
	}

	@Override
	public List<Lecture> findLecturesInThisSubject(String batchId, String subjectId) {
		String sql="select * from Lecture where batchId=? and subjectId=?";
		return template.query(sql, new Object[] {batchId, subjectId}, new BeanPropertyRowMapper<>(Lecture.class));
	}

	@Override
	public void deleteLecture(Lecture lecture) {
		String sql="delete from Lecture where lectureId=? and batchId=? and subjectId=?";
		template.update(sql, lecture.getLectureId(), lecture.getBatchId(), lecture.getSubjectId());
	}
	
}
