package com.kc.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.kc.util.CreateMenu;
import com.kc.util.WeiXinUtil;

public class getnewstest extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		this.doPost(request, response);

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int contentLength = request.getContentLength();
		System.out.println(contentLength);
		System.out.println("请求到了这里");
		
		ServletInputStream inputStream = request.getInputStream();
		
		if (inputStream==null) {
			System.out.println("没有收到请求数据");
		}
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		PrintWriter writer = response.getWriter();
		String line=null;
		System.out.println("zheshizheli");

		while ((line=bufferedReader.readLine())!=null) {
			
		System.out.println(line);	
		
		}
		writer.print("nihaossss");

	}
	
	@Test
	public void test() {
		CreateMenu createMenu = new CreateMenu();
		
		try {
			createMenu.doJsonPost("http://6240283e.ngrok.io/WeiXin/getnewstest",MenuExample.test() );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
