package com.sidney.spring.aop;

import java.lang.reflect.Method;

import javax.security.auth.login.AccountException;

import org.springframework.aop.ThrowsAdvice;

public class ThrowInterceptor implements ThrowsAdvice {
	public void afterThrowing( Method method, Object[]args,Object instance,
			AccountException ex) throws Throwable{
		System.out.println("������" + method.getName() + "������ϣ�����ֵΪ" + ex );
	}
	public void afterThrowing( NullPointerException ex) throws Throwable{
		System.out.println( "�׳��쳣 " + ex );
	}
}
