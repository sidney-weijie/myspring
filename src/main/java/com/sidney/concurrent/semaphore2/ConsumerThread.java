package com.sidney.concurrent.semaphore2;

public class ConsumerThread extends Thread {
	private RePastService service;
	public ConsumerThread(RePastService service){
		super();
		this.service = service;
	}
	
	public void run(){
		service.get();
	}
}
