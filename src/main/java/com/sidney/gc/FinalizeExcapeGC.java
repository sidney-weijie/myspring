package com.sidney.gc;

/**
 * 此代码演示两点
 * 1.对象可以被GC时自我拯救
 * 2.这种自救的机会只有一次，因为一个对象的finalize()方法最多会被系统自动调用一次
 * @author zengweijie
 *
 */
public class FinalizeExcapeGC {

	public static FinalizeExcapeGC SAVE_HOOK = null;
	
	public void isAlive(){
		System.err.println("yes, iam still alive:)");
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.err.println("finalize method executed!");
		
		FinalizeExcapeGC.SAVE_HOOK = this;
	}
	
	public static void main(String[] args) throws Throwable {
		
		SAVE_HOOK = new FinalizeExcapeGC();
		
		//对象第一次成功拯救自己
		SAVE_HOOK = null;
		
		System.gc();
		
		//因为finalize方法优先级低，暂停0.5s等待
		Thread.sleep(500);
		
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.err.println("no i am dead:(");
		}
		
		
		//下面这段代码与上面的完全相同，但这次自我拯救失败了
				SAVE_HOOK = null;
				
				System.gc();
				
				//因为finalize方法优先级低，暂停0.5s等待
				Thread.sleep(500);
				
				if(SAVE_HOOK != null){
					SAVE_HOOK.isAlive();
				}else{
					System.err.println("no i am dead:(");
				}

	}

}
