package com.sidney.concurrent.Phaser;

import java.util.concurrent.Phaser;

public class ThreadC extends Thread{
	private Phaser phaser;
	public ThreadC(Phaser phaser){
		this.phaser = phaser;
	}
	
	public void run(){
		PrintTools.methodB();
	}

}
