package com.kc.news;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PicDownload {

	public static void downPic(String url,String filename,String savePath) throws IOException {
		URL fromurl= new URL(url);
		URLConnection con = fromurl.openConnection();
		con.setConnectTimeout(5*1000);
		InputStream is = con.getInputStream();
		//1k的数据缓冲
		byte[] bs=new byte[1024];
		//读取到的数据长度
		int len;
		//输出的文件流
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		FileOutputStream os = new FileOutputStream(file.getPath()+"\\"+filename);
		
		while ((len=is.read(bs))!=-1) {
			os.write(bs,0,len);
		}
		//完毕，关闭所有连接
		os.close();
		is.close();
	}
}
