package com.ishtoo.pinnacle.dao;

import java.util.List;

import com.ishtoo.pinnacle.models.Lecture;

public interface LectureDao {

	void addLecture(Lecture lecture);

	List<Lecture> findLecturesInThisSubject(String batchId, String subjectId);

	void deleteLecture(Lecture lecture);
	
	boolean checkIfLectureExistsWithSameIdInThisSubject(Lecture lecture);

}
