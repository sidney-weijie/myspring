package com.sidney.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.mysql.jdbc.StringUtils;
import com.sidney.geo.Point;
import com.sidney.geo.Rectangle;
import com.sidney.util.FileUtils;

public class ProcessLines {

	public static String filePath = "";
	
	
	public static void sortResultData(){
		
		
		/*
		
		
		List<String> tmpList = FileUtils.loadFromFile("E:\\china\\result.txt");;
		
		
		for(int i = 0; i < tmpList.size(); i ++ ){
			String tmpStr = tmpList.get(i);
			
			
			if(tmpStr.startsWith("# ")){
				int num =0;
				
				
				String []array1 = tmpStr.split("\\s");
				
				num = Integer.parseInt(array1[1]);
				String location = tmpStr.substring(4, tmpStr.length());
				
				AreaCode areaCode = map.get(location);
				if(areaCode == null) {
					System.err.println("error" + location );
					resultList.add("# " +num + " " + location);
					
				}else{
					resultList.add("# " +num + " " + areaCode.getCode()+" " + location);
					
				}
			
				for(int j = 0; j< num;j++){
					String str = tmpList.get(i+j+1);
					
					resultList.add(str);
				}
				i+=num;
			}
			
		}
		
		FileUtils.writeLineToFile("E:\\china\\resultWithAreaCode.txt",resultList );*/
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		List<String> areacodeList = FileUtils.loadFromFile("E:\\china\\quhua.txt");
		
		List<String> resultList = new ArrayList<String>();
		List<String> rectangleList = new ArrayList<String>();
		
		Map<String,AreaCode> map = new TreeMap<String, AreaCode>();
		for(int i = 0; i< areacodeList.size();i++){
			String str = areacodeList.get(i);
			
			String []strArray = str.split("\\s");
			
			AreaCode areaCode = new AreaCode();
			
			areaCode.setCode(strArray[0]);
			if(strArray.length > 1){
				areaCode.setProv(strArray[1]);
			}
			if(strArray.length > 2){
				areaCode.setCity(strArray[2]);
			}
			if(strArray.length > 3){
				areaCode.setDistrict(strArray[3]);
			}
			
			
			String code = strArray[0];
			
			String addr = str.replace(code, "").replace("\t", "");
			
			System.err.println(addr);
			map.put(addr, areaCode);
			
		}
		
		List<String> tmpList = FileUtils.loadFromFile("E:\\mytest\\waitForProcess.txt");;
		
		
		for(int i = 0; i < tmpList.size(); i ++ ){
			String tmpStr = tmpList.get(i);
			
			System.err.println(tmpStr);
			if(tmpStr.startsWith("#")){
				int num =0;
				
				
				String []array1 = tmpStr.split("\\s");
				
				num = Integer.parseInt(array1[1]);
				String location = tmpStr.substring(4, tmpStr.length());
				
				AreaCode areaCode = map.get(location);
				
				
				String level="";
				if(areaCode == null) {
					System.err.println("error" + location );
					resultList.add("# " +num + " " + location);
					
				}else{
					
					if(areaCode.getCode().endsWith("0000")){
						level = "1";
					}else if(areaCode.getCode().endsWith("00")){
						level = "2";
					}else{
						level = "3";
					}
					
					
					
					
				}
			
				for(int j = 0; j< num;j++){
					String str = tmpList.get(i+j+1);
					List<Point> list = parsePoints(str);
					Rectangle rect = genRect(list);
					resultList.add("# " + areaCode.getCode()+" " + location + " " + level + " " + rect.getLeftDown().getLng() + " " + rect.getLeftDown().getLat() + " " + rect.getRightUp().getLng() + " "  + rect.getRightUp().getLat() );
					//rectangleList.add("# " + areaCode.getCode()+" " + location + " " + level + " " + rect.getLeftDown().getLng() + " " + rect.getLeftDown().getLat() + " " + rect.getRightUp().getLng() + " "  + rect.getRightUp().getLat());
					resultList.add(str);
					
				}
				i+=num;
			}
			
		}
		
		for(int i = 0; i < resultList.size();i++){
			System.err.println(resultList.get(i));
		}
		
		FileUtils.writeLineToFile("E:\\mytest\\areaCode3.txt",resultList );
		FileUtils.writeLineToFile("E:\\mytest\\rectangle3.txt",rectangleList );
		
		
		
	}
	
	
	public static List<Point> parsePoints(String str){
		String [] arrayPoints = str.split(";");
		List<Point> list = new ArrayList<Point>();
		for(int i = 0; i< arrayPoints.length; i++){
			String[] array = arrayPoints[i].split(",");
			if(array.length != 2){
				System.err.println("some error happens" + " " + arrayPoints[i]);
				continue;
			}
			list.add(new Point(Double.parseDouble(array[0]),Double.parseDouble(array[1])));
		}
		
		
		return list;
	}
	
	public static Rectangle genRect(List<Point> list){
		double minY = 1000.0;
		double minX = 1000.0;
		double maxY = 0.0;
		double maxX = 0.0;
		int listSize = list.size();
		for(int i =0 ;i < listSize ;i++){
			Point p = list.get(i);
			if(p.getLat()<minX){
				minX = p.getLat();
			}
			
			if(p.getLat()>maxX){
				maxX = p.getLat();
			}
			
			if(p.getLng()>maxY){
				maxY = p.getLng();
			}
			
			if(p.getLng() < minY){
				minY = p.getLng();
			}
		}
		
		Point p1 = new Point(minX, minY);
		Point p2 = new Point(maxX, maxY);
		Rectangle r = new Rectangle();
		r.setLeftDown(p1);
		r.setRightUp(p2);
		return r;
		
	}
	
	
	public static void processData1(){
		List<String> tmpList = new ArrayList<String>();
		tmpList.add("山西省晋城市城区");
		tmpList.add("内蒙古自治区鄂尔多斯市康巴什区");
		tmpList.add("内蒙古自治区锡林郭勒盟镶黄旗");
		tmpList.add("辽宁省沈阳市辽中区");
		tmpList.add("辽宁省大连市普兰店区");
		tmpList.add("辽宁省盘锦市大洼区");
		tmpList.add("黑龙江省佳木斯市向阳区");
		tmpList.add("黑龙江省佳木斯市郊区");
		tmpList.add("黑龙江省牡丹江市东宁市");
		tmpList.add("江苏省无锡市梁溪区");
		tmpList.add("江苏省无锡市新吴区");
		tmpList.add("江苏省宁波市江东区");
		tmpList.add("安徽省六安市叶集区");
		tmpList.add("山东省菏泽市定陶区");
		tmpList.add("河南省开封市金明区");
		tmpList.add("河南省新乡市红旗区");
		tmpList.add("四川省宜宾市南溪区");
		tmpList.add("四川省阿坝藏族羌族自治州马尔康市");
		tmpList.add("贵州省毕节市大方县");
		tmpList.add("贵州省毕节市金沙县");
		tmpList.add("贵州省毕节市纳雍县");
		tmpList.add("贵州省毕节市赫章县");
		tmpList.add("贵州省毕节市威宁彝族回族苗族自治县");
		tmpList.add("贵州省铜仁市万山区");
		tmpList.add("贵州省铜仁市江口县");
		tmpList.add("贵州省铜仁市玉屏侗族自治县");
		tmpList.add("贵州省铜仁市印江土家族苗族自治县");
		tmpList.add("贵州省铜仁市松桃苗族自治县");
		tmpList.add("贵州省铜仁市德江县");
		tmpList.add("贵州省铜仁市沿河土家族自治县");
		tmpList.add("云南省怒江傈僳族自治州泸水市");
		tmpList.add("云南省迪庆藏族自治州香格里拉市");
		tmpList.add("西藏自治区日喀则市仁布县");
		tmpList.add("西藏自治区日喀则市吉隆县");
		tmpList.add("西藏自治区日喀则市亚东县");
		tmpList.add("西藏自治区昌都市卡若区");
		tmpList.add("西藏自治区昌都市类乌齐县");
		tmpList.add("西藏自治区昌都市江达县");
		tmpList.add("西藏自治区昌都市贡觉县");
		tmpList.add("西藏自治区昌都市丁青县");
		tmpList.add("西藏自治区昌都市察雅县");
		tmpList.add("西藏自治区昌都市八宿县");
		tmpList.add("西藏自治区昌都市洛隆县");
		tmpList.add("西藏自治区昌都市左贡县");
		tmpList.add("西藏自治区昌都市边坝县");
		tmpList.add("西藏自治区昌都市芒康县");
		tmpList.add("西藏自治区山南市扎囊县");
		tmpList.add("西藏自治区山南市乃东区");
		tmpList.add("西藏自治区山南市贡嘎县");
		tmpList.add("西藏自治区山南市桑日县");
		tmpList.add("西藏自治区山南市琼结县");
		tmpList.add("西藏自治区山南市曲松县");
		tmpList.add("西藏自治区山南市措美县");
		tmpList.add("西藏自治区山南市洛扎县");
		tmpList.add("西藏自治区山南市错那县");
		tmpList.add("西藏自治区山南市加查县");
		tmpList.add("西藏自治区山南市隆子县");
		tmpList.add("西藏自治区山南市浪卡子县");
		tmpList.add("陕西省榆林市横山区");
		tmpList.add("新疆维吾尔自治区哈密市伊州区");
		tmpList.add("新疆维吾尔自治区哈密市巴里坤哈萨克自治县");
		tmpList.add("台湾省");
		tmpList.add("澳门特别行政区");
		tmpList.add("香港特别行政区");
		
		
		List<String> srcList = FileUtils.loadFromFile("E:\\china\\partErrorData.txt");
		List<String> errorList = new ArrayList<String>();
		List<String> resultList = new ArrayList<String>();
		
		
		for(int i = 0; i < srcList.size(); i ++ ){
			String tmpStr = srcList.get(i);
			
			
			if(tmpStr.startsWith("##0")){
				errorList.add(tmpStr);
				continue;
			}
			
			if(tmpStr.startsWith("# ")){
				int num =0;
				
				
				String []array1 = tmpStr.split("\\s");
				
				num = Integer.parseInt(array1[1]);
				String location = tmpStr.substring(4, tmpStr.length()-1);
//				System.err.println(num);

				
				
				for(String tmpxxx:tmpList){
					if(tmpxxx.indexOf(array1[2]) >0){
						array1[2] = tmpxxx;
					}
				}
				
				resultList.add("# " +num + " " + array1[2]);
				
				for(int j = 0; j< num;j++){
					String str = srcList.get(i+j+1);
					
					resultList.add(str);
				}
				i+=num;
			}
			
		}
		
		FileUtils.writeLineToFile("E:\\china\\partErrorDataResult.txt", resultList);
		
	}
	
	public static void  processData(){
		List<String> srcList = FileUtils.loadFromFile(filePath);
		List<String> errorList = new ArrayList<String>();
		List<String> resultList = new ArrayList<String>();
		
		
		for(int i = 0; i < srcList.size(); i ++ ){
			String tmpStr = srcList.get(i);
			
			if(null != tmpStr && tmpStr.length()>0 &&!tmpStr.startsWith("**") && !tmpStr.startsWith("##")){
				continue;
			}
			
			if(tmpStr.startsWith("##0")){
				errorList.add(tmpStr);
				continue;
			}
			
			if(tmpStr.startsWith("##")){
				int num =0;
				String tttt = tmpStr.replace("##", "");
				System.err.println(tttt);
				String []array1 = tttt.split("\\s");
				System.err.println(array1[0]);
				num = Integer.parseInt(array1[0]);
				String location = tmpStr.substring(4, tmpStr.length()-1);
//				System.err.println(num);

				
				resultList.add("# " +num + " " + array1[1]);
				
				for(int j = 0; j< num;j++){
					String str = srcList.get(i+j+1);
					str = str.replace("** ", "");
					resultList.add(str);
				}
				i+=num;
			}
			
		}
		
		
			FileUtils.writeLineToFile("E:\\china\\errorDataResult.txt", resultList);
		
		System.err.println("*************************************");
		for(int i = 0; i< errorList.size();i++){
			System.err.println(errorList.get(i));
		}
		
	}
	

}
