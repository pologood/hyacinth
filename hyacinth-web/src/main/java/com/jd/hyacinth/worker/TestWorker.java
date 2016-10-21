package com.jd.hyacinth.worker;

import com.jd.hyacinth.domain.common.WorkerSuper;

public class TestWorker extends WorkerSuper {

	@Override
	protected void dispose() throws Exception {
		// TODO Auto-generated method stub
		logger.info("Worker is running...");
	}

	@Override
	protected String workRunKeyName() {
		
		return "com.jd.hyacinth.test.worker";
	}

}
