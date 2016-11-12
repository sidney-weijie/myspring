package com.sidney.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTest {

	public static void main(String[] args) {
		FutureTest f0 = new FutureTest();
		
		Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				FutureTest f0 = new FutureTest();
				f0.testFuture();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			public void run() {
				FutureTest f0 = new FutureTest();
				f0.testFutureTask();
			}
		});
		t1.start();
		t2.start();
	}
	
	public  void testFuture(){
		ExecutorService executor = Executors.newCachedThreadPool();
		TaskCallable task = new TaskCallable("future");
		Future<Integer> result = executor.submit(task);
		executor.shutdown();
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("主线程在执行任务");
		
		try {
			System.out.println("task运行结果 " + result.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("over");
	}
	
	public  void testFutureTask(){
		ExecutorService executor = Executors.newCachedThreadPool();
		TaskCallable task = new TaskCallable("futureTask");
		FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
		executor.submit(futureTask);
		executor.shutdown();
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("主线程在执行任务");
		
		try {
			System.out.println("运行结果 " + futureTask.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("over");
	}
	
	class TaskCallable implements Callable<Integer>{
		private String name;
		public TaskCallable(String name){
			this.name = name;
		}
		
		public Integer call() throws Exception {
			System.out.println(name + ": 子线程在进行计算");
			Thread.sleep(5000);
			int sum = 0;
			for( int i = 0; i< 100;i++){
				sum += i;
			}
			return sum;
		}
		
	}
	
	
	
}
