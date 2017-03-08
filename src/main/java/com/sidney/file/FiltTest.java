package com.sidney.file;

import java.io.File;

public class FiltTest {
	/**
	 * 获得文件夹下最新的文件，指定后缀,返回最新的文件全路径
	 * @param path
	 * @return
	 */
	public static String getNewestFile(String path,String suffix){
		File file = new File(path);
		File[] files = file.listFiles();
		int flag = -1;
		long modifyTime = 0L;
		for(int i = 0; i< files.length;i++){
			if(files[i].isFile() && files[i].getName().endsWith(suffix) && files[i].lastModified() > modifyTime){
				modifyTime = files[i].lastModified();
				flag = i;
			}
		}
		
		if( flag >= 0 ){
			System.err.println(files[flag].getName()+"    " +  files[flag].getPath());
			return files[flag].getName();
		}
		return null;
		
	}
	
	public static void main(String[] args) {
		
		//getNewestFile("D:/data", "datx");
		
		testClasspath();
	}
	
	
	
	/**
	 * 测试基本的类路径
	 */
	
	public static void testClasspath(){
		System.err.println(ClassLoader.getSystemResource("mybatis-config.xml")); 
		//得到的也是当前ClassPath的绝对URI路径 。
		//如：file：/D：/workspace/jbpmtest3/bin/
		
		System.err.println(Thread.currentThread().getContextClassLoader ().getResource("")); 
	}

}
