package com.baobaotao.advice;



import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeforeAdvice {

	public static void main(String[] args) {
		Waiter target = new NaiveWaiter();
		BeforeAdvice advice = (BeforeAdvice) new GreetingBeforeAdvice();
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget( target);
		pf.addAdvice(advice);
		Waiter proxy = (Waiter) pf.getProxy();
		proxy.greetTo("John");
		proxy.serveTo("Tom");
		
		
		String path = "com/baobaotao/advice/beans.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(path);
		Waiter waiter = (Waiter)ctx.getBean("waiter1");
		waiter.greetTo("zwj");
	}

}
