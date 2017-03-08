package com.sidney.concurrent.Phaser1;

public class ThreadTestPhaseOnAdvance extends Thread {
	private MyService myService;
    public ThreadTestPhaseOnAdvance(MyService myService) {
		 super();
		 this.myService = myService;
		 
    }
    
    public void run(){
    	myService.testMethod();
    }
}
