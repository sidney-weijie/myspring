package com.sidney.concurrent.semaphore1;

public class MyThread  extends Thread{
	private ListPool listPool;
	public MyThread( ListPool listPool){
		super();
		this.listPool = listPool;
	}
	
	public void run(){
		for (int i = 0 ;i < Integer.MAX_VALUE; i ++){
			String getString = listPool.get();
			System.out.println(Thread.currentThread().getName() + " 取得值 " + getString);
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			listPool.put(getString);
		}
	}
}
