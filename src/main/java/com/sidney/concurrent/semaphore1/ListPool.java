package com.sidney.concurrent.semaphore1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ListPool {
	private int poolMaxSize = 3;
	private int semaphorePermits = 5;
	private List<String> list = new ArrayList<String>();
	private Semaphore concurrencySemaphore = new Semaphore(semaphorePermits,false);
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public ListPool(){
		super();
		for(int i = 0 ;i < poolMaxSize;i++){
			list.add("曾伟杰" + (i));
		}
		
	}
	
	public String get(){
		String getString = null;
		try {
			concurrencySemaphore.acquire();
			lock.lock();
			while(list.size() == 0){
				condition.await();
			}
			getString = list.remove(0);
			lock.unlock();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getString;
	}
	
	public void put(String stringValue){
		lock.lock();
		list.add(stringValue);
		condition.signalAll();
		lock.unlock();
		concurrencySemaphore.release();
	}
	
	
}
