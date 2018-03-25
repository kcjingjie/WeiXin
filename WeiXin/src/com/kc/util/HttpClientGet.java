package com.kc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClientGet {
	public static void doget(String url) throws IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity,"UTF-8");
		JSONObject object = JSONObject.fromObject(string);
		System.out.println(string);
	}
	public static void doGetByUrl(String url) throws IOException {
		try {
			URL url2 = new URL(url);
			
			HttpURLConnection openConnection =(HttpURLConnection) url2.openConnection();
			
			openConnection.setRequestMethod("GET");
			
			openConnection.connect();
			
			InputStream inputStream = openConnection.getInputStream();
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
			String line;
			while ((line=bufferedReader.readLine())!=null) {
				System.out.println("使用的是url请求！");
				System.out.println(line);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
