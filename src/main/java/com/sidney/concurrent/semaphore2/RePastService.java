package com.sidney.concurrent.semaphore2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 厨师与客人就餐
 * @author Sidney
 *
 */
public class RePastService {
	volatile private Semaphore setSemaphore = new Semaphore(10); //厨师
	volatile private Semaphore getSemaphore = new Semaphore(20); //就餐者
	volatile private ReentrantLock lock = new ReentrantLock();
	volatile private Condition setCondition = lock.newCondition();
	volatile private Condition getCondition = lock.newCondition();
	//producePosition变量的最多只有4个盒子存放菜品
	volatile private Object[] producePosition = new Object[4];
	
	private boolean isEmpty(){
		boolean isEmpty = true;
		for( int i = 0 ;i<producePosition.length;i++){
			if(producePosition[i] != null){
				isEmpty = false;
				break;
			}
		}
		if(isEmpty == true){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isFull(){
		boolean isFull = true;
		for(int i=0;i<producePosition.length;i++){
			if(producePosition[i] == null){
				isFull = false;
				break;
			}
		}
		return isFull;
	}
	
	public void set(){
		try {
			setSemaphore.acquire(); //允许同时最多有10个厨师进行生产
			lock.lock();
			while(isFull()){
				
				System.out.println(Thread.currentThread().getName() + "  生产者在等待");
				setCondition.await();
				
			}
			for(int i = 0 ;i < producePosition.length ; i++){
				if(producePosition[i] == null){
					producePosition[i] = " DATA ";
					System.out.println(Thread.currentThread().getName() + "  Produced  " + producePosition[i]);
					break;
				}
			}
			Thread.sleep(200);
			getCondition.signalAll();
			lock.unlock();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			setSemaphore.release();
		}
	}
	
	public void get(){
		try {
			getSemaphore.acquire();
			lock.lock();
			while(isEmpty()){
				System.out.println(Thread.currentThread().getName() + "  消费者在等待");
				getCondition.await();
			}
			for(int i = 0; i<producePosition.length;i++){
				if(producePosition[i] != null){
					System.out.println(Thread.currentThread().getName() + "  consumed  " + producePosition[i]);
					producePosition[i] = null;
					break;
				}
			}
			Thread.sleep(200);
			setCondition.signalAll();
			lock.unlock();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			getSemaphore.release();
		}
	}
	
}
