package com.sidney.springBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.alibaba.fastjson.JSON;

/**
 * 测试Spring Bean的生命周期 
 * @author zengweijie
 *
 */
public class Car implements BeanNameAware,BeanFactoryAware,InitializingBean,DisposableBean{
	private String brand;
	private String color;
	private int maxSpeed;
	private BeanFactory beanFactory;
	private String beanName;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public Car(String brand, String color, int maxSpeed) {
		super();
		this.brand = brand;
		this.color = color;
		this.maxSpeed = maxSpeed;
	}
	
	public Car(){
		System.out.println("调用Car的构造方法");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("调用 DisposableBean的destroy()方法");
		
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("调用InitializingBean的afterPropertiesSet()方法");
		
	}
	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		System.out.println("调用  BeanFactoryAware的setBeanFactory()方法");
		this.beanFactory = arg0;
		
	}
	@Override
	public void setBeanName(String arg0) {
		System.out.println("调用BeanNameAware的setBeanName()方法");
		this.beanName = arg0;
		
	}
	
	public void myInit(){
		System.out.println("myInit方法");
		this.brand = "兰博基尼";
	}
	
	
	public void myDestroy(){
		System.out.println("myDestory方法");
	}
	
	public void introduce(){
		System.out.println(JSON.toJSONString(this));
	}
}
