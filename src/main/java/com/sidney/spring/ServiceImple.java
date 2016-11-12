package com.sidney.spring;

public class ServiceImple implements IService {
	private IDao dao;
	public void service(String name) {
		// TODO Auto-generated method stub
		System.out.println(dao.sayHello(name));
	}
	public IDao getDao() {
		return dao;
	}
	public void setDao(IDao dao) {
		this.dao = dao;
	}
	
	
	
}
