package com.sidney.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {

	public static void main(String[] args) {
		
		//ExecutorService executor = Executors.newSingleThreadExecutor();
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		
		TestThread tmp1 = new TestThread();
		tmp1.setThreadName("I am one");
		tmp1.setSleepMills(5000);
		
		TestThread tmp2 = new TestThread();
		tmp2.setThreadName("I am two");
		tmp2.setSleepMills(3000);
		
		TestThread tmp3 = new TestThread();
		tmp3.setThreadName("I am three");
		
		tmp3.setSleepMills(6000);
		
		
		TestThread tmp4 = new TestThread();
		tmp4.setThreadName("I am four");
		
		tmp4.setSleepMills(6000);
		
		
		executor.execute(tmp1);
		executor.execute(tmp2);
		executor.execute(tmp3);
		executor.execute(tmp4);
		
		System.err.println("Main Thread finished.");
		
		

	}
	


}
