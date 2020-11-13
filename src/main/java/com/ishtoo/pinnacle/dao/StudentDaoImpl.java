package com.ishtoo.pinnacle.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ishtoo.pinnacle.models.Student;

@Repository
public class StudentDaoImpl implements StudentDao {
	
	@Autowired
	JdbcTemplate template;
	
	@Override
	public void addStudent(Student student) {
		String sql="insert into Student(studentName, dateOfBirth, gender, address, contactNumber, batchId, username) values(?, ?, ?, ?, ?, ?, ?)";
		template.update(sql, student.getStudentName(), student.getDateOfBirth(), student.getGender(), student.getAddress(), student.getContactNumber(), student.getBatchId(), student.getUsername());
	}
	
	@Override
	public Student findByUsername(String username) {
		String sql="select * from Student where username=?";
		try {
			return (Student)template.queryForObject(sql, new Object[] {username}, new BeanPropertyRowMapper<>(Student.class));
		}
		catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Student> searchStudentByName(String studentName) {
		String sql="select * from Student where studentName like ?";
		String temp='%'+studentName+'%';
		return template.query(sql, new Object[] {temp}, new BeanPropertyRowMapper<>(Student.class));
	}

	@Override
	public List<Student> findStudentsInThisBatch(String batchId) {
		String sql="select * from Student where batchId=?";
		return template.query(sql, new Object[] {batchId}, new BeanPropertyRowMapper<>(Student.class));
	}

	@Override
	public List<Student> findAllStudents() {
		String sql="select * from Student";
		return template.query(sql, new BeanPropertyRowMapper<>(Student.class));
	}
}
