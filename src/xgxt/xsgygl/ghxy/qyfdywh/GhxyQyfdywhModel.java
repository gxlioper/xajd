package xgxt.xsgygl.ghxy.qyfdywh;

import xgxt.utils.Pages;

public class GhxyQyfdywhModel {
	private String []xqqdm;
	private String []CheckVal;
	private String []zgh;
	private String []yqqdm;
	private String yqdm;
	
	
	private String queryequals_bmdm;
	
	private Pages pages=new Pages();
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getQueryequals_bmdm() {
		return queryequals_bmdm;
	}
	
	public String[] getYqqdm() {
		return yqqdm;
	}
	public void setYqqdm(String[] yqqdm) {
		this.yqqdm = yqqdm;
	}
	public void setQueryequals_bmdm(String queryequals_bmdm) {
		this.queryequals_bmdm = queryequals_bmdm;
	}
	
	public String[] getCheckVal() {
		return CheckVal;
	}
	public void setCheckVal(String[] checkVal) {
		CheckVal = checkVal;
	}
	public void setZgh(String[] zgh) {
		this.zgh = zgh;
	}
	public String[] getZgh() {
		return zgh;
	}
	public String[] getXqqdm() {
		return xqqdm;
	}
	public void setXqqdm(String[] xqqdm) {
		this.xqqdm = xqqdm;
	}
	public String getYqdm() {
		return yqdm;
	}
	public void setYqdm(String yqdm) {
		this.yqdm = yqdm;
	}


}

