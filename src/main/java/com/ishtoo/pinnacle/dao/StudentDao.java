package com.ishtoo.pinnacle.dao;

import java.util.List;

import com.ishtoo.pinnacle.models.Student;

public interface StudentDao {

	void addStudent(Student student);

	Student findByUsername(String username);

	List<Student> searchStudentByName(String studentName);

	List<Student> findStudentsInThisBatch(String batchId);

	List<Student> findAllStudents();
}
