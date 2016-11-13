package com.sidney.concurrent.Phaser;

import java.util.concurrent.Phaser;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Phaser phase = new Phaser(3);
		PrintTools.phaser = phase;
		ThreadA a = new ThreadA(phase);
		a.setName("A");
		a.start();
		
		ThreadA b = new ThreadA(phase);
		b.setName("B");
		b.start();
		
		ThreadA c = new ThreadA(phase);
		c.setName("C");
		c.start();
		
	}

}
