package com.sidney.concurrent;

public class TestThread implements Runnable{
	
	
	private String threadName;
	private long sleepMills;
	
	
	public long getSleepMills() {
		return sleepMills;
	}



	public void setSleepMills(long sleepMills) {
		this.sleepMills = sleepMills;
	}



	public String getThreadName() {
		return threadName;
	}



	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}



	@Override
	public void run() {
		
		System.err.println("Thread + " + threadName + " started! processing");
		try {
			Thread.sleep(sleepMills);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.err.println("Thread + " + threadName + " finished! processing");
		
	}
	
}