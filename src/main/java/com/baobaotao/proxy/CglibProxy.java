package com.baobaotao.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {
	private Enhancer enhancer = new Enhancer();

	public Object getProxy(Class clazz) {
		//设置需要创建子类的类
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		//通过字节码技术动态创建子类实例
		return enhancer.create();
	}
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		//拦截你类所有方法的调用
		PerformanceMonitor.begin(obj.getClass().getName()+"."+method.getName());
		//通过代理类调用父类中的方法
		Object result=proxy.invokeSuper(obj, args);
		PerformanceMonitor.end();
		return result;
	}
}
