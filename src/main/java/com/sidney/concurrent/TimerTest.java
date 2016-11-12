package com.sidney.concurrent;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
					System.out.println("abc");
			}
		}, 5000,1000);
	
		
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e1) {
			
			e1.printStackTrace();
		}
		timer.cancel();
		  String a = "hello2"; 
	        final String b = "hello";
	        String d = "hello";
	        String c = b + 2; 
	        String e = d + 2;
	        System.out.println((a == c));
	        System.out.println((a == e));
	        
	        System.out.println("a=" + a);
	        System.out.println("b=" + b);
	        System.out.println("c=" + c);
	        System.out.println("d=" + d);
	        System.out.println("e=" + e);
		
		
	}
	
	public class MyTimerTask extends TimerTask{

		@Override
		public void run() {
			
			
		}
		
	}
	
}
