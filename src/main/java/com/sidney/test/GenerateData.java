package com.sidney.test;

import java.util.ArrayList;
import java.util.List;

import com.sidney.util.FileUtils;

public class GenerateData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>(1000000);
		
		for(long i = 14500000000L; i<14500988490L;i++){
			list.add(""+i);
		}
		FileUtils.writeLineToFile("D:\\tmpdata.txt", list);
	}

}
