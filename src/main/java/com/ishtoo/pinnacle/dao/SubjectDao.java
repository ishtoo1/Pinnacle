package com.ishtoo.pinnacle.dao;

import java.util.List;

import com.ishtoo.pinnacle.models.Subject;

public interface SubjectDao {

	boolean checkIfSubjectExistsInBatch(String subjectId, String batchId);

	void addSubject(Subject subject);

	List<Subject> findAllSubjects();

	void changeSubjectTeacher(Subject subject);

	List<Subject> findSubjectsInThisBatch(String batchId);

	void deleteSubject(String batchId, String subjectId);

	Subject findSubjectById(String batchId, String subjectId);

	List<Subject> findSubjectsTaughtByTeacher(int teacherId);

	List<Subject> findSubjectsInThisBatchTaughtByTeacher(String batchId, int teacherId);

}
