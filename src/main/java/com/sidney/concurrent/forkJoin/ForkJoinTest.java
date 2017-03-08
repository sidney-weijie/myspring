package com.sidney.concurrent.forkJoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

interface Filter{
	boolean accept(double t);
}

class Counter extends RecursiveTask<Integer>{

	@Override
	protected Integer compute() {
		
		if(to - from < THREADHOLD){
			int count = 0;
			for(int i = from;i<to ;i ++){
				if(filter.accept(values[i]) )count ++;
				
			}
			return count;
		}else{
			int mid = (from + to )/2;
			Counter first = new Counter(values, from, mid, filter);
			Counter second = new Counter(values, mid, to, filter);
			invokeAll(first,second);
			return first.join() + second.join();
		}
		
	}
	public static final int THREADHOLD = 1000;
	private double[] values;
	private int from;
	private int to;
	private Filter filter;
	
	public Counter(double [] values,int from, int to ,Filter filter){
		this.values = values;
		this.from = from;
		this.to = to;
		this.filter = filter;
		this.values = values;
	}
	
	
}