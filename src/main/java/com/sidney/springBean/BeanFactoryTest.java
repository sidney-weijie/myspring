package com.sidney.springBean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class BeanFactoryTest {

	public static void main(String[] args) {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		org.springframework.core.io.Resource res = resolver.getResource("classpath:com/sidney/springBean/beans.xml");
		
		BeanFactory bf = new XmlBeanFactory(res);
		System.out.println("init BeanFactory");
		
		Car car = bf.getBean("car1",Car.class);
		System.out.println("car bean is ready for user!");

	}

}
