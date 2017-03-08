package com.sidney.concurrent.semaphore2;

public class ProviderThread extends Thread{
	private RePastService service;
	public ProviderThread(RePastService service){
		super();
		this.service = service;
	}
	
	public void run(){
		service.set();
	}
}
