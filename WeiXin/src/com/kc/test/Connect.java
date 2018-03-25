package com.kc.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.dom4j.DocumentException;

import com.kc.news.AccessToken;
import com.kc.news.ImageMessage;
import com.kc.news.MessageUtil;
import com.kc.news.PicDownload;
import com.kc.news.TextMessage;
import com.kc.test2.MenuExample;
import com.kc.util.CreateMenu;
import com.kc.util.HttpClientGet;
import com.kc.util.WeiXinUtil;

public class Connect extends HttpServlet {

	// 消息的类型
	public static String MESSAGE_TEXT = "text";

	public static String MESSAGE_IMAGE = "image";

	public static String MESSAGE_VOICE = "voice";

	public static String MESSAGE_VIDEO = "video";

	public static String MESSAGE_SHORTVIDEO = "shortvideo";

	public static String MESSAGE_EVENT = "event";

	public static String MESSAGE_NEWS = "news";

	public static String CREATE_MENU="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		WeiXinConnect weiXinConnect = new WeiXinConnect();
		// 最近的验证方法
		// boolean validate = weiXinConnect.validate(signature, timestamp,
		// nonce);
		// 之前的验证方法
		boolean is = CheckUtil.checkSignature(signature, timestamp, nonce);
		if (is) {
			System.out.println("xiangtaong");
			response.getWriter().write(request.getParameter("echostr"));
			response.getWriter().close();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 设置响应的格式是utf-8
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		try {
			Map<String, String> map = MessageUtil.xmlToMap(request);
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String createTimeString = map.get("CreateTime");
			String msgType = map.get("MsgType");
			// 判断消息格式
			String message = null;
			if (Connect.MESSAGE_TEXT.equals(msgType)) {
				String content = map.get("Content");

				if ("2".equals(content)) {
					message = MessageUtil.initNewsMessage(toUserName,
							fromUserName);
				} else if ("3".equals(content)) {
					CreateMenu createMenu = new CreateMenu();
					String url=CREATE_MENU.replace("ACCESS_TOKEN", WeiXinUtil.getAccessToken().getToken());
					createMenu.doJsonPost(url,MenuExample.test() );
					
					//使用WEiXinUTil的httpclient方法
					//JSONObject doPostStr = WeiXinUtil.doPostStr(url, TestJsonObeject.test());
					//System.out.println(doPostStr.toString());
					return;
				}else{
					TextMessage initText = MessageUtil.initText(toUserName,
							fromUserName);
					initText.setCreateTime(new Date().getTime());
					initText.setContent("您发送的消息是" + content);
					message = MessageUtil.textMessageToXml(initText);

					String accessToken = WeiXinUtil.getAccessToken().getToken();

					String replace = WeiXinUtil.DELETE_URL.replace(
							"ACCESS_TOKEN", accessToken);
					
					//使用httpclient进行get请求
					//HttpClientGet.doget(replace);
					//使用UrlConnetction进行get请求
					HttpClientGet.doGetByUrl(replace);
				}
			} else if (Connect.MESSAGE_EVENT.equals(msgType)) {
				String event = map.get("Event");
				if (event.equals("subscribe")) {
					TextMessage initText = MessageUtil.initText(toUserName,
							fromUserName);
					initText.setContent("欢迎订阅！");
					message = MessageUtil.textMessageToXml(initText);
				} else if(event.equals("CLICK")) {
					TextMessage initText = MessageUtil.initText(toUserName, fromUserName);
					initText.setContent("这时怎样的世界！让我们一起来探寻！");
					message = MessageUtil.textMessageToXml(initText);
				}else if (event.equals("")) {
					
				}
			} else if (Connect.MESSAGE_IMAGE.equals(msgType)) {
				String picUrl = map.get("PicUrl");
				System.out.println(picUrl);
				// 下载图片
				String savePath = "C:\\Users\\kc\\Pictures\\WeiXin";
				String file = savePath + "\\ly3.jpg";
				// 测试地址
				// String savePath="\\Image\\";
				PicDownload.downPic(picUrl, "ly3.jpg", savePath);
				ImageMessage initPic = MessageUtil.initPicMessage(toUserName,
						fromUserName);
				try {
					String mediaId = WeiXinUtil.upload(file, WeiXinUtil
							.getAccessToken().getToken(), "image");
					initPic.setMediaId("oNNBGNEuwvIkdI115ItH8kXXKF9ETuN7zO5wLiRkLdGpA0VtCaw2aipeJkB2FuvO");
				} catch (KeyManagementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchProviderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				message = MessageUtil.picMessageToXml(initPic);
				System.out.println(message);
			}
			out.print(message);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

}
