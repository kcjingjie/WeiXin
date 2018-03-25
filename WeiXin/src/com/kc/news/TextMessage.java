package com.kc.news;

public class TextMessage extends BaseMessage{

	private String Content;
	private int MsgId;

	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getMsgId() {
		return MsgId;
	}
	public void setMsgId(int msgId) {
		MsgId = msgId;
	}
	
	
	
}
