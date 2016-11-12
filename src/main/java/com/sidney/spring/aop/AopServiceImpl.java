package com.sidney.spring.aop;

import javax.security.auth.login.AccountException;

public class AopServiceImpl implements IAopService {
	private String name;
	public void withAop() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("有AOP函数运行。name: " + name);
		if ( name.trim().length() == 0 ){
			throw new AccountException("name 属性不能为空");
		}
	}

	public void withOutAop() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("没有AOP函数运行");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
