/*
 * Copyright (C) 2011-2013 ShenZhen iBoxpay Information Technology Co. Ltd.
 * 
 * All right reserved.
 * 
 * This software is the confidential and proprietary information of iBoxPay Company of China. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement you entered into with iBoxpay inc.
 *
 * $Id: HttpUtil.java 2013-12-18 下午03:04:49 liuyinghua@iboxpay.com $
 * 
 * Create on 2013-12-18 下午03:04:49
 * 
 * Description: 
 *
 */
package com.sidney.util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 功能说明: <br>
 * ============================================================================<br>
 * 开发人员: liuyinghua@iboxpay.com <br>
 * ----------------------------------------------------------------------------<br>
 * 开发时间: 2013-12-18<br>
 * ----------------------------------------------------------------------------<br>
 * 更新描叙:
 * 
 */
public class HttpUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	public static String getMimeType(byte[] bytes) {
		String suffix = getFileSuffix(bytes);
		String mimeType;

		if ("JPG".equals(suffix)) {
			mimeType = "image/jpeg";
		} else if ("GIF".equals(suffix)) {
			mimeType = "image/gif";
		} else if ("PNG".equals(suffix)) {
			mimeType = "image/png";
		} else if ("BMP".equals(suffix)) {
			mimeType = "image/bmp";
		}else {
			mimeType = "application/octet-stream";
		}

		return mimeType;
	}
	
	/**
	 * 获取文件的真实后缀名。目前只支持JPG, GIF, PNG, BMP四种图片文件。
	 * 
	 * @param bytes 文件字节流
	 * @return JPG, GIF, PNG or null
	 */
	public static String getFileSuffix(byte[] bytes) {
		if (bytes == null || bytes.length < 10) {
			return null;
		}

		if (bytes[0] == 'G' && bytes[1] == 'I' && bytes[2] == 'F') {
			return "GIF";
		} else if (bytes[1] == 'P' && bytes[2] == 'N' && bytes[3] == 'G') {
			return "PNG";
		} else if (bytes[6] == 'J' && bytes[7] == 'F' && bytes[8] == 'I' && bytes[9] == 'F') {
			return "JPG";
		} else if (bytes[0] == 'B' && bytes[1] == 'M') {
			return "BMP";
		} else {
			return null;
		}
	}
	
}
