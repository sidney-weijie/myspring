package com.sidney.spring;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class SpringTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("resources/applicationContext.xml"));
		IService hello = (IService) factory.getBean("service");
		hello.service("Sidney");
		factory.destroySingletons();
	}

}
