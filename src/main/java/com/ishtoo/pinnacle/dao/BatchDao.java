package com.ishtoo.pinnacle.dao;

import java.util.List;

import com.ishtoo.pinnacle.models.Batch;

public interface BatchDao {

	List<Batch> findAllOpenBatches();
	List<Batch> findAllClosedBatches();
	void addBatch(Batch batch);
	void openAdmissionsForBatch(Batch batch);
	void closeAdmissionsForBatch(Batch batch);
	List<Batch> findAllBatches();
	boolean checkIfBatchAlreadyExists(Batch batch);
	void deleteBatch(String batchId);
	Batch findBatchById(String batchId);
	List<Batch> findAllBatchesHavingTeacher(int teacherId);
}
