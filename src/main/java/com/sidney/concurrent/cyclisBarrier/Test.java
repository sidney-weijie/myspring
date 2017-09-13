package com.sidney.concurrent.cyclisBarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Test {

	public static void main(String[] args) {
		
		CyclicBarrier cbRef = new CyclicBarrier(2);
		MyService service = new MyService(cbRef);
		
		List<ThreadA> list = new ArrayList<ThreadA>();
		for(int i = 0; i<4;i++){
			ThreadA tmp = new ThreadA(service);
			tmp.setName("A"+i);
			list.add(tmp);
		}
		for(ThreadA tmp:list){
			tmp.start();
		}
	}

}
