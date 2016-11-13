package com.sidney.concurrent.semaphore;

import java.util.ArrayList;
import java.util.List;

public class Run {

	public static void main(String[] args) throws InterruptedException {
		Service service = new Service();

		List<MyThread> list = new ArrayList<MyThread>();
		char character = 'A';
		for(int i = 0; i < 10 ;i ++){
			MyThread thread = new MyThread(service,("A" + i) );
			list.add(thread);
		}
		
		for(MyThread thread:list){
			thread.start();
		}
		
		
		Thread.sleep(60000);
		
		MyThread.run = false;
		

	}

}