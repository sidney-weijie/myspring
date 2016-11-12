package com.sidney.spring.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class MethodBeforeInterceptor implements MethodBeforeAdvice{

	public void before(Method method, Object[] args, Object instance)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("即将要执行方法: " + method.getName() );
		
		if( instance instanceof AopServiceImpl){
			String name = ((AopServiceImpl) instance).getName();
			if( null == name){
				throw new NullPointerException("name 属性不能为null");
			}
		}
	}
	
}
