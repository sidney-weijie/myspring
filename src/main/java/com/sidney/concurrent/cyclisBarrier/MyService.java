package com.sidney.concurrent.cyclisBarrier;

import java.util.concurrent.CyclicBarrier;

public class MyService {
	private CyclicBarrier cbRef;
	public MyService(CyclicBarrier cbRef){
		this.cbRef = cbRef;
	}
	public void beginRun(){
		try {
			long sleepValue = (int) (Math.random()*5000);
			Thread.sleep(sleepValue);
			System.out.println(Thread.currentThread().getName() + "  " + 
			System.currentTimeMillis() + "  begin跑第一阶段    " +
					(cbRef.getNumberWaiting() + 1));
			cbRef.await();
			System.out.println(Thread.currentThread().getName() + "  " + 
			System.currentTimeMillis() + "  end跑第一阶段    " +
					(cbRef.getNumberWaiting() ));
			
			sleepValue = (int) (Math.random()*5000);
			Thread.sleep(sleepValue);
			System.out.println(Thread.currentThread().getName() + "  " + 
					System.currentTimeMillis() + "  begin跑第二阶段    " +
							(cbRef.getNumberWaiting() + 1));
			cbRef.await();
			System.out.println(Thread.currentThread().getName() + "  " + 
					System.currentTimeMillis() + "  end跑第二阶段    " +
							(cbRef.getNumberWaiting() ));
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

