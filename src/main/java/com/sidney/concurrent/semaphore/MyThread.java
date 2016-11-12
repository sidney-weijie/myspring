package com.sidney.concurrent.semaphore;

public class MyThread  extends Thread{
	private Service service;
	private String name;
	public static boolean run = true;
	public MyThread(Service service,String name){
		super();
		this.service = service;
		this.name = name;
	}
	
	public void run(){
		Thread.currentThread().setName(name);
		while(run){
			service.testMethod();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
