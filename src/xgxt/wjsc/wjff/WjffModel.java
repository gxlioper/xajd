package xgxt.wjsc.wjff;

import xgxt.utils.Pages;

public class WjffModel {

	Pages pages = new Pages();
	
	private String wjh;//文件号
	
	private String wjm;//文件名
	
	private String ffr;//发放人

	private String wjffKssj;//文件发放开始时间
	
	private String wjffJssj;//文件发放结束时间
	
	public String getFfr() {
		return ffr;
	}
	
	public void setFfr(String ffr) {
		this.ffr = ffr;
	}
	
	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getWjh() {
		return wjh;
	}

	public void setWjh(String wjh) {
		this.wjh = wjh;
	}

	public String getWjm() {
		return wjm;
	}

	public void setWjm(String wjm) {
		this.wjm = wjm;
	}

	public String getWjffKssj() {
		return wjffKssj;
	}

	public void setWjffKssj(String wjffKssj) {
		this.wjffKssj = wjffKssj;
	}

	public String getWjffJssj() {
		return wjffJssj;
	}

	public void setWjffJssj(String wjffJssj) {
		this.wjffJssj = wjffJssj;
	}

	
}
