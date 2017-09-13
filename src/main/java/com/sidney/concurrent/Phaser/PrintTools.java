package com.sidney.concurrent.Phaser;

import java.util.concurrent.Phaser;

public class PrintTools {
	public static Phaser phaser;
	public static void methodA(){
		System.out.println(Thread.currentThread().getName() + " A1 begin=" + System.currentTimeMillis());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + " A1 end=" + System.currentTimeMillis());
		
		System.out.println(Thread.currentThread().getName() + " A2 begin=" + System.currentTimeMillis());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		phaser.arriveAndAwaitAdvance();
		
		System.out.println(Thread.currentThread().getName() + " A2 end=" + System.currentTimeMillis());
	}
	
	public static void methodB(){
		try {
			System.out.println(Thread.currentThread().getName() + " A1 begin=" + System.currentTimeMillis());
			Thread.sleep(3000);
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName() + " A1 end=" + System.currentTimeMillis());
			
			System.out.println(Thread.currentThread().getName() + " A2 begin=" + System.currentTimeMillis());
			Thread.sleep(3000);
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName() + " A2 end=" + System.currentTimeMillis());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
}
