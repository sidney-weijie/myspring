package com.sidney.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Junit4ExceptionTest {
	private String str;
	@Before
	public void init(){
		str = null;
	}
	/**
	 * 测试异常
	 */
	@Test(expected=NullPointerException.class)
	public void test(){
		assertNotNull(str.charAt(10));
	}
}
