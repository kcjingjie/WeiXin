package com.kc.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;








import com.kc.test.Connect;

public class CreateMenu {
		
	public void doJsonPost(String urlPost,String Json) {
		
		// TODO Auto-generated method stub
		String result="";
		BufferedReader reader =null;
		try {
			URL url = new URL(urlPost);
			HttpURLConnection con =(HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			//设置文件类型
			con.setRequestProperty("Content-Type", "application/json");

			//往服务器里面发送数据
			if (Json!=null&&!"".equals(Json)) {
				byte[] writesbytes = Json.getBytes();
				con.setRequestProperty("Content-Length", String.valueOf(writesbytes.length));
				
				con.connect();
				
				OutputStream outputStream = con.getOutputStream();
				
				outputStream.write(Json.getBytes("utf-8"));
				outputStream.flush();
				outputStream.close();
			}
				String responseMessage = con.getResponseMessage();
				System.out.println(responseMessage);
				System.out.println("获得了返回的状态码。");
				 reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
				 String line=null;
				 while ((line=reader.readLine())!=null) {
					System.out.println(line);
				}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
