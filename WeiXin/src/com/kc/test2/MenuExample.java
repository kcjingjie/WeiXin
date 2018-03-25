package com.kc.test2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.kc.news.Button;
import com.kc.news.ClickButton;
import com.kc.news.Menu;
import com.kc.news.ViewButton;


public class MenuExample {

	public static String test() throws UnsupportedEncodingException {
		
		Menu menu = new Menu();
		
		ViewButton viewButton = new ViewButton();
		viewButton.setName("国学经典");
		viewButton.setType("view");
		viewButton.setUrl("http://www.baidu.com");
		
		ClickButton clickButton = new ClickButton();
		clickButton.setName("kc境界");
		clickButton.setKey("kcsdf");
		clickButton.setType("click");
		
		Button button = new Button();
		
		ClickButton clickButton2 = new ClickButton();
		clickButton2.setKey("saoma");
		clickButton2.setType("scancode_push");
		clickButton2.setName("扫码");
		
		ClickButton clickButton3 = new ClickButton();
		clickButton3.setKey("ditu");
		clickButton3.setName("地图");
		clickButton3.setType("location_select");
		
		button.setName("测试");
		button.setSub_button(new Button[]{clickButton2,clickButton3});
		
		menu.setButton(new Button[]{viewButton,clickButton,button});
		
		JSONObject fromObject = JSONObject.fromObject(menu);
		
		String string = fromObject.toString();
		
		return string;
	}
	//测试字符集
	@Test
	public void testEncoding(){
		String csn = Charset.defaultCharset().name();
		System.out.println("字符集："+csn);
	}
	
	
	
}
