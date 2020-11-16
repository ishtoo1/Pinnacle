package com.ishtoo.pinnacle.dao;

import java.util.List;

import com.ishtoo.pinnacle.models.Test;

public interface TestDao {
	void addTest(Test test);

	List<Test> findAllUpcomingTests();

	List<Test> findAllTests();

	void deleteTest(String testId);

	Test findById(int testId);
}
