package com.baobaotao.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {
	private Enhancer enhancer = new Enhancer();

	public Object getProxy(Class clazz) {
		//������Ҫ�����������
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		//ͨ���ֽ��뼼����̬��������ʵ��
		return enhancer.create();
	}
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		//�����������з����ĵ���
		PerformanceMonitor.begin(obj.getClass().getName()+"."+method.getName());
		//ͨ����������ø����еķ���
		Object result=proxy.invokeSuper(obj, args);
		PerformanceMonitor.end();
		return result;
	}
}
