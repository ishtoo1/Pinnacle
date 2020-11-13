package com.ishtoo.pinnacle.dao;

import java.util.List;

import com.ishtoo.pinnacle.models.Teacher;

public interface TeacherDao {
	void addTeacher(Teacher teacher);
	Teacher findByUsername(String username);
	List<Teacher> searchTeacherByName(String teacherName);
	List<Teacher> findAllTeachers();
	Teacher findById(int teacherId);
}
