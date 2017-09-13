package com.sidney.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestWeb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "https://auth.api.mucfc.com/register/sendSmsVerifyCode.json";
		try {
			Map<String,String> param = new HashMap<String, String>();
			param.put("mobile", "18926038996");
			String result = AbstractWebUtils.doPost(url, param, 5000, 5000);
			System.err.println(result);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
