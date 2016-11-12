package com.sidney.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
	//线程池最大限制数
	private static final int MAX_WORKER_NUMBERS = 10;
	//线程程置默认的数量
	private static final int DEFAULT_WORKER_NUMBERS = 5;
	//线程池最小的数量
	private static final int MIN_WORKER_NUMBERS = 1;
	//这是一个 工作工作列表，将会向里面
	private final LinkedList<Job> jobs = new LinkedList<Job>();
	
	//工作者列表 
	private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
	
	private int workerNum = DEFAULT_WORKER_NUMBERS;
	private AtomicLong threadNum = new AtomicLong();
	
	public DefaultThreadPool (int num){
		workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS? MIN_WORKER_NUMBERS : num;
		initializeWorkers(workerNum);
	}
	
	public void execute(Job job){
		if( null != job){
			//添加一个工作，然后进行通知
			synchronized (jobs) {
				jobs.addLast(job);
				jobs.notify();
			}
		}
	}
	
	public void shutdown(){
		for( Worker worker: workers){
			worker.shutdown();
		}
	}
	
	public void addWorkers(int num){
		synchronized (jobs) {
			if( num + this.workerNum > MAX_WORKER_NUMBERS){
				num = MAX_WORKER_NUMBERS - this.workerNum;
			}
			initializeWorkers(num);
			this.workerNum += num;
		}
	}
	
	public void removeWorkers(int num){
		synchronized (jobs) {
			if( num >= this.workerNum ){
				throw new IllegalArgumentException("beyond workNum");
			}
			//按照给定的数量停止Worker
			int count = 0;
			while ( count < num){
				Worker worker = workers.get(count);
				if(workers.remove(worker)){
					worker.shutdown();
					count++;
				}
			}
			this.workerNum -= num;
		}
	}
	
	public int getJobSize(){
		return jobs.size();
	}
	private void initializeWorkers( int num){
		for ( int i = 0 ;i < num; i++){
			Worker worker = new Worker();
			workers.add(worker);
			Thread thread = new Thread(worker,"ThreadPool-Worker-" + threadNum.incrementAndGet());
			thread.start();
		}
	}
	
	class Worker implements Runnable{
		//是否工作
		private volatile boolean running = true;
		
		public void run() {
			
			while( running ){
				Job job = null;
				synchronized (jobs) {
					while( jobs.isEmpty() ){
						try {
							jobs.wait();
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
							return;
						}
					}
					job = jobs.removeFirst();
				}
				if( null != job ){
					try{
						job.run();
					}catch (Exception ex){
						//忽略Job执行中的exception
					}
				}
			}
		}
		public void shutdown(){
			running = false;
		}
		
	}


}
