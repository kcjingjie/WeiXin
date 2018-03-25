package com.kc.news;

import java.util.List;

public class NewsMessage extends BaseMessage{
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<News> getArticles() {
		return Articles;
	}
	public void setArticles(List<News> articles) {
		Articles = articles;
	}
	private int ArticleCount;
	private List<News> Articles;
}
