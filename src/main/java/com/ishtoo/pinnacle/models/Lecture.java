package com.ishtoo.pinnacle.models;

public class Lecture {
	private int lectureId;
	private String lectureName;
	private String subjectId;
	private String batchId;

	public int getLectureId() {
		return lectureId;
	}

	public void setLectureId(int lectureId) {
		this.lectureId = lectureId;
	}

	public String getLectureName() {
		return lectureName;
	}

	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	@Override
	public String toString() {
		return "Lecture [lectureId=" + lectureId + ", lectureName=" + lectureName + ", subjectId=" + subjectId
				+ ", batchId=" + batchId + "]";
	}

}
