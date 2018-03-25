package com.kc.news;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.kc.test.PicUpload;
import com.kc.util.WeiXinUtil;

public class WeiXinTets {
	public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
		AccessToken token = WeiXinUtil.getAccessToken();
		System.out.println("票据:"+token.getToken());
		System.out.println("有效时间:"+token.getExpiresIn());
		String mediaId = PicUpload.upload("C:\\Users\\kc\\Pictures\\WeiXin\\ly.jpg", token.getToken(), "image");
		//String mediaId = WeiXinUtil.upload("C:\\Users\\kc\\Pictures\\WeiXin\\ly.jpg", token.getToken(), "image");
		System.out.println(mediaId);
	}
}
