package com.sidney.spring;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.MethodBeforeAdvice;

public class MethodBeforeAdviceImpl implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object obj)
			throws Throwable {
		// TODO Auto-generated method stub
		System.err.println("运行前检查...");
		System.err.println("Method: " + method.getName());
		System.err.println("Args: " + Arrays.asList(args));
		
		System.err.println("Object: " + obj );
	}
	
}
