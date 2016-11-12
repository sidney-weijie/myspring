package com.sidney.spring.aop;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

@SuppressWarnings("deprecation")
public class AopRun {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("resources/aop-spring.xml"));
		IAopService hello = ( IAopService )factory.getBean("aopService");
		
		hello.withAop();
		hello.withOutAop();
		factory.destroySingletons();
	}

}
