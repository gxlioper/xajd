package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.zqsz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class ZqszForm extends ActionForm {
	private String zbid;
	private String zbzc;
	private String zbksrq;
	private String zbjsrq;
	private String xn;
	private String xq;
	private String czsj;
	private String ybid;
	private String yf;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	public String getYbid() {
		return ybid;
	}
	public void setYbid(String ybid) {
		this.ybid = ybid;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	public String getZbid() {
		return zbid;
	}
	public void setZbid(String zbid) {
		this.zbid = zbid;
	}
	public String getZbzc() {
		return zbzc;
	}
	public void setZbzc(String zbzc) {
		this.zbzc = zbzc;
	}
	public String getZbksrq() {
		return zbksrq;
	}
	public void setZbksrq(String zbksrq) {
		this.zbksrq = zbksrq;
	}
	public String getZbjsrq() {
		return zbjsrq;
	}
	public void setZbjsrq(String zbjsrq) {
		this.zbjsrq = zbjsrq;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getCzsj() {
		return czsj;
	}
	public void setCzsj(String czsj) {
		this.czsj = czsj; 
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages page) {
		this.pages = page;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	
}
