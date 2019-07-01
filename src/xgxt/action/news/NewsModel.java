package xgxt.action.news;

import xgxt.utils.Pages;

public class NewsModel {

	Pages pages = new Pages();
	
	private String newsTitle;
	
	private String pubKssj;
	
	private String pubJssj;
	
	private String tagId;
	
	private String puber;
	

	public String getPuber() {
		return puber;
	}

	public void setPuber(String puber) {
		this.puber = puber;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}


	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getPubKssj() {
		return pubKssj;
	}

	public void setPubKssj(String pubKssj) {
		this.pubKssj = pubKssj;
	}

	public String getPubJssj() {
		return pubJssj;
	}

	public void setPubJssj(String pubJssj) {
		this.pubJssj = pubJssj;
	}
}
