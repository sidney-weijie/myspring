package com.sidney.concurrent.Phaser1;

import java.util.concurrent.Phaser;

public class TestOnAdvanceRun {

	public static void main(String[] args) {
	
		Phaser phaser = new Phaser(2){
			protected boolean onAdvance(int phase,int registeredParties) {
				System.out.println("protected boolean onAdvance(int phase,int registeredParties) is invoked");
				return false;
				//返回true，不等待了，Phaser呈现无效/销毁状态
				//返回false， 则Phaser继续工作
			}
		};
		MyService service = new MyService(phaser);
		ThreadTestPhaseOnAdvance threadA = new ThreadTestPhaseOnAdvance(service);
		threadA.setName("A");
		ThreadTestPhaseOnAdvance threadB = new ThreadTestPhaseOnAdvance(service);
		threadB.setName("B");
		threadA.start();
		threadB.start();
			
	}

}
