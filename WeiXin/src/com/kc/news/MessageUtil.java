package com.kc.news;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.kc.test.Connect;
import com.kc.util.WeiXinUtil;
import com.thoughtworks.xstream.XStream;


public class MessageUtil {
	/**
	 * 将XML文本转化为Map对象
	 * 
	 * @author kc
	 * 
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request)
			throws DocumentException {

		Map<String, String> map = new HashMap<>();

		SAXReader saxReader = new SAXReader();

		try {
			InputStream inputStream = request.getInputStream();

			Document doc = saxReader.read(inputStream);

			Element root = doc.getRootElement();

			List<Element> elements = root.elements();

			for (Element e : elements) {
				map.put(e.getName(), e.getText());
			}

			inputStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;

	}
	public static String textMessageToXml(TextMessage textMessage) {
		
		XStream xStream = new XStream();
		xStream.alias("xml", textMessage.getClass());
		return xStream.toXML(textMessage);
		
	}
	
	public static TextMessage initText(String toUserName,String fromUserName) {
		TextMessage textMessage = new TextMessage();
		textMessage.setFromUserName(toUserName);
		textMessage.setToUserName(fromUserName);
		textMessage.setMsgType(Connect.MESSAGE_TEXT);
		textMessage.setCreateTime(new Date().getTime());
		return textMessage;
	}
	public static String newsMessageToXml(NewsMessage newsMessage) {
		
		XStream xStream = new XStream();
		xStream.alias("xml", newsMessage.getClass());
		xStream.alias("item", new News().getClass());
		
		return xStream.toXML(newsMessage);
		
	}
	/**
	 * 返回图文消息
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initNewsMessage(String toUserName,String fromUserName) {
		List<News>  newslist= new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		
		News news = new News();
		news.setTitle("慕课网");
		news.setDescription("学习IT的好地方，提供各种学习资料。");
		news.setPicUrl("http://fb65455c.ngrok.io/WeiXin/Image/ly.jpg");
		news.setUrl("www.zhiboba.com");
		newslist.add(news);
		News news2 = new News();
		news2.setTitle("足球");
		news2.setDescription("操你大爷的");
		news2.setPicUrl("http://fb65455c.ngrok.io/file/sss.jpg");
		news2.setUrl("www.zhiboba.com");
		newslist.add(news2);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(Connect.MESSAGE_NEWS);
		newsMessage.setArticles(newslist);
		newsMessage.setArticleCount(newslist.size());
		return newsMessageToXml(newsMessage);
	}
	public static ImageMessage initPicMessage(String toUserName,String fromUserName) {
		ImageMessage imageMessage = new ImageMessage();
		
		imageMessage.setFromUserName(toUserName);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setMsgType(Connect.MESSAGE_IMAGE);
		
		return imageMessage;
		
	}
	public static String picMessageToXml(ImageMessage imageMessage) {
		
		XStream xStream = new XStream();
		xStream.alias("xml", imageMessage.getClass());
		return xStream.toXML(imageMessage);
		
	}
}


