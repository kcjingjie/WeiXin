package com.kc.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeiXinConnect {
	
	 private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',  
	        '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
	//验证字符串是否正确
	public boolean validate(String signature,String timestamp,String nonce) {
		// TODO Auto-generated method stub
		List list=new ArrayList<String>();
		list.add("kcjingjie");
		list.add(timestamp);
		list.add(nonce);
		//字典排序
		Collections.sort(list);
		String s="";
		for (int i = 0; i < list.size(); i++) {
			s=(String) list.get(i)+s;
		}
		System.out.println("signature:"+signature+"total:"+s);
		if (encode("SHA1", s).equalsIgnoreCase(signature)) {
			return true;
		}else {
			return false;
		}
	} 
	public static String encode(String algorithm,String str) {
		if (str==null) {
			return null;
		}
		//java自带的加密类
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			//转为byte
			messageDigest.update(str.getBytes());
			
			return getForamttedText(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
		
	}
	
	public static String getForamttedText(byte[] bytes) {
		
		int len = bytes.length;
		
		StringBuilder buf = new StringBuilder(len*2);
		//把密文转化成16进制的字符串形式
		for (int j = 0; j <len; j++) {
			 buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);  
	         
			 buf.append(HEX_DIGITS[bytes[j] & 0x0f]);  		}
		
		return buf.toString();
		
	}
	
	
}
