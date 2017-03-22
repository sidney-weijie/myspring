package com.sidney.http;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.StringUtils;
import com.sidney.md5.FileSafeCode;

public class HttpDownload {

	/** 
     * 从网络Url中下载文件 
     * @param urlStr 
     * @param fileName 
     * @param savePath 
     * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 * @throws OutOfMemoryError 
     */  
    public static String  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException, OutOfMemoryError, NoSuchAlgorithmException{  
        URL url = new URL(urlStr);    
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
        //设置超时间为3秒  
        conn.setConnectTimeout(3*1000);  
        //防止屏蔽程序抓取而返回403错误  
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
        String sha1 = conn.getHeaderField("ETag");
        if(sha1 == null || sha1.length() <4){
        	return "";
        }
        sha1 = sha1.substring(5);
        System.err.println(sha1);
        
        //得到输入流 
        InputStream inputStream = conn.getInputStream();    
        //获取自己数组 
        byte[] getData = readInputStream(inputStream);      
        
        //文件保存位置  
        File saveDir = new File(savePath);  
        if(!saveDir.exists()){  
            saveDir.mkdir();  
        }  
        File file = new File(saveDir+File.separator+fileName);      
        FileOutputStream fos = new FileOutputStream(file);     
       
        fos.write(getData);   
        if(fos!=null){  
            fos.close();    
        }  
        if(inputStream!=null){  
            inputStream.close();  
        }  
       String fileSha1 =  FileSafeCode.getSha1(file);
       if(sha1.equals(fileSha1)){
    	   return sha1;
       }else{
    	   file.delete();   
       }
        return "";
  
    }  
  
  
  
    /** 
     * 从输入流中获取字节数组 
     * @param inputStream 
     * @return 
     * @throws IOException 
     */  
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
        byte[] buffer = new byte[1024];    
        int len = 0;    
        ByteArrayOutputStream bos = new ByteArrayOutputStream();    
        while((len = inputStream.read(buffer)) != -1) {    
            bos.write(buffer, 0, len);    
        }    
        bos.close();    
        return bos.toByteArray();    
    }    
    
    public static String getTimeShort() {
    	   SimpleDateFormat formatter = new SimpleDateFormat("YYMMddHHmmss");
    	   Date currentTime = new Date();
    	   String dateString = formatter.format(currentTime);
    	   return dateString;
    	}
  
    public static void main(String[] args) {  
        try{  
        	String nowDate = getTimeShort();
            String sha = downLoadFromUrl("http://user.ipip.net/download.php?type=datx&token=7cb1361a6b5b960b7035075740dbe11ebf9fd338",  
                    "ipdatx_" + nowDate + ".datx" ,"D:\\data");  
            System.err.println("sha=" + sha);
        }catch (Exception e) {  
            e.printStackTrace();
        }  
    }  
}
