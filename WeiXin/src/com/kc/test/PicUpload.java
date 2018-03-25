package com.kc.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

import com.kc.news.MessageUtil;
import com.kc.util.WeiXinUtil;

public class PicUpload {
		
		public static String upload(String filepath,String accessToken,String type) throws IOException {
			
				File file = new File(filepath);
				if (!file.exists()) {
					System.out.println("文件不存在");
				}
				String url=WeiXinUtil.UPLAOD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
				System.out.println(url);
				//连接
				URL urlToUpload = new URL(url);
				HttpURLConnection con = (HttpURLConnection)urlToUpload.openConnection();
				con.setDoInput(true);
				con.setDoOutput(true);
				con.setRequestMethod("POST");
				con.setUseCaches(true);
				//设置请求头信息
				con.setRequestProperty("Connection", "Keep-Alive");
				con.setRequestProperty("Charset", "UTF-8");
				//设置边界
				String BOUNDARY="------"+System.currentTimeMillis();
				con.setRequestProperty("Content-Type", "multipart/form-data;boundary="+BOUNDARY);
				
				OutputStream outputStream = con.getOutputStream();
				
				byte[] end_data =("\r\n--"+BOUNDARY+"--\r\n").getBytes();
				byte[] b=new byte[1024];
				int len;									
				FileInputStream is = new FileInputStream(file);
				
				StringBuilder sb = new StringBuilder();
				sb.append("--");
				sb.append(BOUNDARY);
				sb.append("\r\n");
				
				sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
				sb.append("Content-Type:application/octet-stream\r\n\r\n");

				byte[] head = sb.toString().getBytes("utf-8");


				outputStream.write(head);
				
				while ((len=is.read(b))!=-1) {
					outputStream.write(b, 0, len);
				}
				System.out.println("......长传完毕。。。。。");
				
				InputStream inputStream = con.getInputStream();
								
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				
				String line=null;
				
				StringBuffer stringBuffer = new StringBuffer();
				
				while ((line=bufferedReader.readLine())!=null) {
					stringBuffer.append(line);
				}
				
				String resultString=null;
				if (resultString==null) {
					resultString=stringBuffer.toString();
				}
				JSONObject jsonObject = JSONObject.fromObject(resultString);
				
				System.out.println(jsonObject.toString());
				
				String mediaId = jsonObject.getString("media_id");
				
				return mediaId;
		
		}
		
}
