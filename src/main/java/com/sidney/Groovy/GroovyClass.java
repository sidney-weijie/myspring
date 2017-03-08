package com.sidney.Groovy;
import com.alibaba.fastjson.JSONObject;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
public class GroovyClass {
	private Class aClass;
	private GroovyObject groovyObject;
	
	
	public Object invoke(String function,JSONObject params){
		
		
		Object result = null;
		try {	
			if(groovyObject != null){
				result = groovyObject.invokeMethod(function, params);		
			}else{
				System.err.println("groovyObject is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result == null){
			return params;
		}
			
		return result;
	}
	
	public GroovyClass(){
		
	}
	
	public GroovyClass(String text) {
		
		try {
			aClass = new GroovyClassLoader().parseClass(text);
			 groovyObject = (GroovyObject) aClass.newInstance();
			 
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {		
			e.printStackTrace();
		}
	}	
	
	public Class getaClass() {
		return aClass;
	}

	public void setaClass(Class aClass) {
		this.aClass = aClass;
	}

	public GroovyObject getGroovyObject() {
		return groovyObject;
	}

	public void setGroovyObject(GroovyObject groovyObject) {
		this.groovyObject = groovyObject;
	}

}
