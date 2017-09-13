package com.sidney.concurrent.semaphore2;

public class Run {

	public static void main(String[] args) throws InterruptedException {
	
		RePastService service = new RePastService(); 
		ProviderThread []pArray = new ProviderThread[60];
		ConsumerThread []cArray = new ConsumerThread[60];
		for( int i = 0; i < 60;i++){
			pArray [i] = new ProviderThread(service);
			cArray [i] = new ConsumerThread(service);
		}
		Thread.sleep(2000);
		for(int i = 0; i< 60;i++){
			pArray[i].start();
			cArray[i].start();
		}
	}

}
