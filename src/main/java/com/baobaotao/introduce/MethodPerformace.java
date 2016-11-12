package com.baobaotao.introduce;

public class MethodPerformace {
	private long begin;
	private long end;
	private String serviceMethod;
    public MethodPerformace(String serviceMethod){
    	reset(serviceMethod);
    }
    public void printPerformace(){
        end = System.currentTimeMillis();
        long elapse = end - begin;
        System.out.println(serviceMethod+"����"+elapse+"���롣");
    }
    public void reset(String serviceMethod){
    	this.serviceMethod = serviceMethod;
    	this.begin = System.currentTimeMillis();
    }
}
