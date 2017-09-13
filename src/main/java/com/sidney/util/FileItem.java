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
 * $Id: FileItem.java 2013-12-18 下午03:03:56 liuyinghua@iboxpay.com $
 * 
 * Create on 2013-12-18 下午03:03:56
 * 
 * Description: 
 *
 */
package com.sidney.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


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
public class FileItem {
	
	private String fileName;
	private String mimeType;
	private byte[] content;
	private File file;

	/**
	 * 基于本地文件的构造器。
	 * 
	 * @param file
	 *            本地文件
	 */
	public FileItem(File file) {
		this.file = file;
	}

	/**
	 * 基于文件绝对路径的构造器。
	 * 
	 * @param filePath
	 *            文件绝对路径
	 */
	public FileItem(String filePath) {
		this(new File(filePath));
	}

	/**
	 * 基于文件名和字节流的构造器。
	 * 
	 * @param fileName
	 *            文件名
	 * @param content
	 *            文件字节流
	 */
	public FileItem(String fileName, byte[] content) {
		this.fileName = fileName;
		this.content = content;
	}

	/**
	 * 基于文件名、字节流和媒体类型的构造器。
	 * 
	 * @param fileName
	 *            文件名
	 * @param content
	 *            文件字节流
	 * @param mimeType
	 *            媒体类型
	 */
	public FileItem(String fileName, byte[] content, String mimeType) {
		this(fileName, content);
		this.mimeType = mimeType;
	}

	public String getFileName() {
		if (this.fileName == null && this.file != null && this.file.exists()) {
			this.fileName = file.getName();
		}
		return this.fileName;
	}

	public String getMimeType() throws IOException {
		if (this.mimeType == null) {
			this.mimeType = HttpUtil.getMimeType(getContent());
		}
		return this.mimeType;
	}

	public byte[] getContent() throws IOException {
		if (this.content == null && this.file != null && this.file.exists()) {
			InputStream in = null;
			ByteArrayOutputStream out = null;

			try {
				in = new FileInputStream(this.file);
				out = new ByteArrayOutputStream();
				int ch;
				while ((ch = in.read()) != -1) {
					out.write(ch);
				}
				this.content = out.toByteArray();
			} finally {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			}
		}
		return this.content;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
}
