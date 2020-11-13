package com.ishtoo.pinnacle.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ishtoo.pinnacle.models.Teacher;

@Repository
public class TeacherDaoImpl implements TeacherDao {
	
	@Autowired
	JdbcTemplate template;
	
	@Override
	public void addTeacher(Teacher teacher) {
		String sql="insert into Teacher(teacherName, dateOfBirth, gender, address, contactNumber, joiningDate, username) values(?, ?, ?, ?, ?, ?, ?)";
		template.update(sql, teacher.getTeacherName(), teacher.getDateOfBirth(), teacher.getGender(), teacher.getAddress(), teacher.getContactNumber(), teacher.getJoiningDate(), teacher.getUsername());
	}
	
	@Override
	public Teacher findByUsername(String username) {
		String sql="select * from Teacher where username=?";
		try {
			return (Teacher)template.queryForObject(sql, new Object[] {username}, new BeanPropertyRowMapper<>(Teacher.class));
		}
		catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public Teacher findById(int teacherId) {
		String sql="select * from Teacher where teacherId=?";
		try {
			return (Teacher)template.queryForObject(sql, new Object[] {teacherId}, new BeanPropertyRowMapper<>(Teacher.class));
		}
		catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Teacher> findAllTeachers() {
		String sql="select * from Teacher";
		return template.query(sql, new BeanPropertyRowMapper<>(Teacher.class));
	}
	
	@Override
	public List<Teacher> searchTeacherByName(String teacherName) {
		String sql="select * from Teacher where teacherName like ?";
		String temp='%'+teacherName+'%';
		return template.query(sql, new Object[] {temp}, new BeanPropertyRowMapper<>(Teacher.class));
	}
}
