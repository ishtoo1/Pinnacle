package com.ishtoo.pinnacle.dao;

import java.util.List;

import com.ishtoo.pinnacle.models.Batch;
import com.ishtoo.pinnacle.models.BatchTestRelation;
import com.ishtoo.pinnacle.models.Test;

public interface BatchTestRelationDao {
	boolean checkExistence(BatchTestRelation batchTestRelation);

	void addTestToBatch(BatchTestRelation batchTestRelation);

	List<Test> findUpcomingTestsNotInThisBatch(String batchId);

	List<Test> findTestsInThisBatch(String batchId);

	void deleteTestFromBatch(String batchId, String testId);

	List<Batch> findBatchesInTest(int testId);

	List<Test> findUpcomingTestsInThisBatch(String batchId);
}
