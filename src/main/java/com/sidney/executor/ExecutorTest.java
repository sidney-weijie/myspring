package com.sidney.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {
	public static void main(String []args){
		ScheduledExecutorService  executorService = Executors.newSingleThreadScheduledExecutor();
		
		Job job = new Job();
		Job job1 = new Job();
		Job job2 = new Job();
		
		
		
		executorService.scheduleAtFixedRate(job, 2, 5, TimeUnit.SECONDS);
		executorService.scheduleAtFixedRate(job1, 3, 5, TimeUnit.SECONDS);
		executorService.scheduleAtFixedRate(job2, 4, 5, TimeUnit.SECONDS);

		
		
	}
}
