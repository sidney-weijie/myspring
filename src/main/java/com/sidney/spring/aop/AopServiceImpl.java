package com.sidney.spring.aop;

import javax.security.auth.login.AccountException;

public class AopServiceImpl implements IAopService {
	private String name;
	public void withAop() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("��AOP�������С�name: " + name);
		if ( name.trim().length() == 0 ){
			throw new AccountException("name ���Բ���Ϊ��");
		}
	}

	public void withOutAop() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("withoutAoP");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
