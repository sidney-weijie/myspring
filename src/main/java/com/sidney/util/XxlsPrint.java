package com.sidney.util;

import java.sql.SQLException;
import java.util.List;

public class XxlsPrint extends XxlsAbstract {
	private int count = 0;
	@Override
	public void optRows(int sheetIndex,int curRow, List<String> rowlist) throws SQLException {	
		System.err.println("curRow=" + curRow);
		for (int i = 0; i < rowlist.size(); i++) {
			System.out.print("'" + rowlist.get(i) + "',");		
		}
		count++;
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		XxlsPrint howto = new XxlsPrint();
		howto.processOneSheet("D:/test1.xlsx",1);
		//	howto.processAllSheets("F:/new.xlsx");
		howto.printCount();
	}
	public void printCount(){
		System.err.println("Count=" + count);
	}
	
}