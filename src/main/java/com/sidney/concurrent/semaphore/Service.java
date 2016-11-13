package com.sidney.concurrent.semaphore;

import java.util.concurrent.Semaphore;

public class Service {
	private boolean isFair = false;
	private Semaphore semaphore = new Semaphore(6,isFair);   //
	public void testMethod(){
		try {
			semaphore.acquire();
			System.out.println(Thread.currentThread().getName() + "  begin timer=" + System.currentTimeMillis());
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + "  end timer=" + System.currentTimeMillis() + " available semphore= " + semaphore.availablePermits());
			
			semaphore.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}