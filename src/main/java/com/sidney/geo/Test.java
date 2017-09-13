package com.sidney.geo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sidney.util.FileUtils;

public class Test {

	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String, String>();
		InputStreamReader read = null;
		BufferedReader reader = null;
		String filePath = "E:\\china\\amapResult.txt";
		try {
			File file = new File(filePath);
			read = new InputStreamReader(new FileInputStream(file),"UTF-8");
			reader = new BufferedReader(read);
			
			String lineTxt = null;
			while((lineTxt=reader.readLine()) != null){
				String[]lineArray = lineTxt.split(" ");
				map.put(lineArray[1], lineArray[2]);
			}
			
			reader.close();
			read.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		List<String> list = FileUtils.loadFromFile("E:\\china\\quhua.txt");
		
		for(String str :list){
			String []strArray = str.split("\t");
			//System.err.println(strArray[0] + " " + strArray[1]);
			if(!map.containsKey(strArray[0])){
				System.err.println(str);
			}
		}

	}

}
