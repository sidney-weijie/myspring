package com.sidney.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	/**
	 * 按行读取文件
	 * 字符类型为UTF-8
	 * @return
	 */
	public static List<String> loadFromFile(String filePath){
		List<String> list = new ArrayList<String>();
		InputStreamReader read = null;
		BufferedReader reader = null;
		try {
			File file = new File(filePath);
			read = new InputStreamReader(new FileInputStream(file),"UTF-8");
			reader = new BufferedReader(read);
			
			String lineTxt = null;
			while((lineTxt=reader.readLine()) != null){
				list.add(lineTxt);
			}
			
			reader.close();
			read.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	/**
	 * 将数据按行写到指定文件
	 * @param filePath
	 * @return
	 */
	public static boolean writeLineToFile(String filePath,List<String> list){
		if(list == null || list.size() == 0){
			System.err.println("file is empty");
			return false;
		}
		
		try {
			File file = new File(filePath);
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			BufferedWriter writer = new BufferedWriter(out);
			
			for(String lineTxt:list){
				writer.write(lineTxt);
				writer.newLine();
			}
			
			
			
			writer.close();
			out.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		
			return false;
		}
		
		
		return true;
	}
	
	
	public static void main(String[]args){
		ArrayList<String> list = new ArrayList<String>();
		list.add("123456");
		list.add("123432fds");
		list.add("fdsaljfldaskf");
		
		writeLineToFile("D:\\testline.txt", list);
	}
	
}
