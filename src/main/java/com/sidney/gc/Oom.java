package com.sidney.gc;

import java.util.ArrayList;
import java.util.List;

public class Oom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Oom> list = new ArrayList<Oom>();
		
		while(true){
			Oom o = new Oom();
			list.add(o);
		}
		
	}

}
