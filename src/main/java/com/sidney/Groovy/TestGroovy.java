package com.sidney.Groovy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class TestGroovy {

	public static Object invoke(String code, String function, JSONObject params){
		try {
			
			//Class aClass = new GroovyClassLoader().par(code);
			Class aClass = new GroovyClassLoader().parseClass(code);
			GroovyObject groovyObject = (GroovyObject) aClass.newInstance();
			
			return groovyObject.invokeMethod(function, params);
		} catch (Exception e) {
			System.err.println("Process Groovy code error" + e.getMessage());
		}
		
		return null;
	}
	
	public static void main(String []args) throws InstantiationException, IllegalAccessException{
	/*	String script="";//groovy script
		ClassLoader parent = ClassLoader.getSystemClassLoader();
		GroovyClassLoader loader = new GroovyClassLoader(parent);
		Class< ?> clazz = loader.parseClass(script);
		GroovyObject clazzObj = (GroovyObject)clazz.newInstance();
		System.out.println(clazzObj.invokeMethod("test", "str"));*/
		
		
		String code = "import com.alibaba.fastjson.JSON; "
				+ "def JSON f(JSON jobj) { jobj.put(\"temp\",\"abc\");"
				+ "    return jobj; "
				+ "}";
		String filename = "groovy.gy";
		String scriptStr = new String();
		try {
			
			File file = getFile(filename);
			InputStream in = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while((line=br.readLine())!=null){
				buffer.append(line);
			}
			scriptStr = buffer.toString();
			System.err.println(scriptStr);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

		}
		
		
		GroovyClass  gc = new GroovyClass(scriptStr);
		
		
		
		
		JSONObject jobj = new JSONObject();
		jobj.put("AAA", "1");
		jobj.put("BBB", "2");
		jobj.put("CCC", "3");
		long start = System.currentTimeMillis();
		
		for(int i = 0; i < 1000000; i++){
			jobj.put("abce"+i, i);
			gc.invoke("verify", jobj);
		}
		
		System.out.println("timeused="+(System.currentTimeMillis() - start));
		//System.err.println(JSON.toJSONString(jobj));
		
		
		
	}
	
	
	  private static File getFile(String fileName) {
	        ClassLoader classLoader = TestGroovy.class.getClassLoader();
	        /**
	        getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件 的绝对路径
	        */
	        URL url = classLoader.getResource(fileName);
	        /**
	         * url.getFile() 得到这个文件的绝对路径
	         */
	        System.out.println(url.getFile());
	        File file = new File(url.getFile());
	        System.out.println(file.exists());	
	        return file;
	    }
}
