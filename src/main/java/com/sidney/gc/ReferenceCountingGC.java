package com.sidney.gc;

/**
 * 对旬A和对象B相互引用
 * @author zengweijie
 *
 */
public class ReferenceCountingGC {

	public Object instance = null;
	
	private static final int _1MB = 1024* 1024;
	
	public static void main(String[] args) {
		
		ReferenceCountingGC objA = new ReferenceCountingGC();
		ReferenceCountingGC objB = new ReferenceCountingGC();
		
		objA.instance = objB;
		objB.instance = objA;
		
		System.gc();

	}

}
