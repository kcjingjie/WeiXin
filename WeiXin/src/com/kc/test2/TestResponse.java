package com.kc.test2;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class TestResponse extends HttpServlet {

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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write("nihao");
		out.flush();
		out.close();
	}
	@Test
	public  void name() {
		  System.out.println(System.getProperty("file.encoding"));     
		System.out.println(Charset.defaultCharset().name());
		System.out.println(System.getProperty("user.language"));  
	}
	@Test
	public void testEncoding() throws UnsupportedEncodingException{
		
		String string="境界";
		
		String string2 = new String(string.getBytes(), "utf-8");
		
		String string3 = new String(string.getBytes("GBK"),"GBK");
		
		String string4 = new String(string.getBytes("utf-8"), "ISO8859-1");
		
		String string5 = new String(string4.getBytes("ISO8859-1"), "utf-8");

		System.out.println(string2);
		
		System.out.println(string3);
		
		String csn = Charset.defaultCharset().name();
		System.out.println("字符集："+csn);
		
	}
}