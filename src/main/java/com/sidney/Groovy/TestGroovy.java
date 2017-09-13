package com.sidney.Groovy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sidney.util.Helper;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class TestGroovy  {

	@Test
	public void testGroovyScript(){
		String scriptStr = getScriptText();
		GroovyClass  gc = new GroovyClass(scriptStr);
		long start = System.currentTimeMillis();
		
		Map<String, String> cmccMap = new HashMap<String, String>();
		Map<String, String> unicomMap = new HashMap<String, String>();
		Map<String, String> telcomMap = new HashMap<String, String>();
		cmccMap.put("0", "0");
		cmccMap.put("1", "0");
		cmccMap.put("2", "0");
		cmccMap.put("3", "0");
		cmccMap.put("4", "1");
		cmccMap.put("5", "1");
		cmccMap.put("6", "1");
		cmccMap.put("7", "1");
		cmccMap.put("8", "0");
		cmccMap.put("9", "0");
		
		unicomMap.put("0", "0");
		unicomMap.put("1", "0");
		unicomMap.put("2", "0");
		unicomMap.put("3", "1");
		unicomMap.put("4", "1");
		unicomMap.put("5", "1");
		unicomMap.put("6", "1");
		unicomMap.put("7", "1");
		unicomMap.put("8", "0");
		unicomMap.put("9", "0");
		
		telcomMap.put("0", "0");	
		telcomMap.put("1", "0");	
		telcomMap.put("2", "1");		
		telcomMap.put("3", "1");
		telcomMap.put("4", "1");
		telcomMap.put("5", "1");
		telcomMap.put("6", "1");
		telcomMap.put("7", "1");
		telcomMap.put("8", "1");
		telcomMap.put("9", "0");	
		
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		System.err.println(JSON.toJSONString(map));
		map = (Map) gc.invoke("verify", map);
		System.err.println(JSON.toJSONString(map));
		Assert.assertEquals(null, map.get("cmccOnlineTime") );
		Assert.assertEquals(null, map.get("unicomOnlineTime") );
		Assert.assertEquals(null, map.get("telcomOnlineTime") );
		
		for(int i = 0;i<10;i++){
			map.put("mobileInnetPeriodSjs", ""+i);
			map.put("mobileNoInUseTimeLt", ""+i);
			map.put("inTimeTc", ""+i);
			System.err.println(JSON.toJSONString(map));
			map = (Map) gc.invoke("verify", map);
			System.err.println(JSON.toJSONString(map));
			
			System.out.println("i=" + i +"  cmccOnlineTime=" + map.get("cmccOnlineTime"));
			System.out.println("i=" + i +"  unicomOnlineTime=" + map.get("unicomOnlineTime"));
			System.out.println("i=" + i +"  telcomOnlineTime=" + map.get("telcomOnlineTime"));
			Assert.assertEquals(cmccMap.get(""+i), map.get("cmccOnlineTime") );
			Assert.assertEquals(unicomMap.get(""+i), map.get("unicomOnlineTime") );
			Assert.assertEquals(telcomMap.get(""+i), map.get("telcomOnlineTime") );
		}
		
		
		
		
		
		System.out.println("timeused="+(System.currentTimeMillis() - start));
	}
	
	
	
	
	
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
				buffer.append('\n');
			}
			scriptStr = buffer.toString();
			System.err.println(scriptStr);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

		}
		
		
		GroovyClass  gc = new GroovyClass(scriptStr);
		
		
		

	
		
		
		
	}
	

	
	
	public static String getScriptText(){
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
				buffer.append('\n');
			}
			scriptStr = buffer.toString();
			System.err.println(scriptStr);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

		}
		
		return scriptStr;
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
