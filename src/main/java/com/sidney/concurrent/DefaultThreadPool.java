package com.sidney.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
	//�̳߳����������
	private static final int MAX_WORKER_NUMBERS = 10;
	//�̳߳���Ĭ�ϵ�����
	private static final int DEFAULT_WORKER_NUMBERS = 5;
	//�̳߳���С������
	private static final int MIN_WORKER_NUMBERS = 1;
	//����һ�� ���������б�����������
	private final LinkedList<Job> jobs = new LinkedList<Job>();
	
	//�������б� 
	private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
	
	private int workerNum = DEFAULT_WORKER_NUMBERS;
	private AtomicLong threadNum = new AtomicLong();
	
	public DefaultThreadPool (int num){
		workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS? MIN_WORKER_NUMBERS : num;
		initializeWorkers(workerNum);
	}
	
	public void execute(Job job){
		if( null != job){
			//���һ��������Ȼ�����֪ͨ
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
			//���ո���������ֹͣWorker
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
		//�Ƿ���
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
						//����Jobִ���е�exception
					}
				}
			}
		}
		public void shutdown(){
			running = false;
		}
		
	}


}
