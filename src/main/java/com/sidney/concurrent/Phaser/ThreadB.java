package com.sidney.concurrent.Phaser;

import java.util.concurrent.Phaser;

public class ThreadB extends Thread{
	private Phaser phaser;
	public ThreadB(Phaser phaser){
		this.phaser = phaser;
	}
	
	public void run(){
		PrintTools.methodA();
	}

}
