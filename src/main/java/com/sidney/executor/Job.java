package com.sidney.executor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Job implements Runnable{
	private String name;
	@Override
	public void run() {
		try {
			System.out.println("Test : + " +  new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis())));
			Thread.sleep(6000);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
}
