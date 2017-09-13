package com.sidney.concurrent.semaphore1;

public class Run {

	
	
	public static void main(String[] args) {
		ListPool pool = new ListPool();
		MyThread[] threadsArray = new MyThread[12];
		for( int i = 0; i<threadsArray.length;i++){
			threadsArray[i] = new MyThread(pool);
		}
		
		for(int i = 0; i<threadsArray.length;i++){
			threadsArray[i].start();
		}
	}

}
