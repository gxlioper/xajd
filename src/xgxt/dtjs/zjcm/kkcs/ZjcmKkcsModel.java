package xgxt.dtjs.zjcm.kkcs;

import xgxt.utils.Pages;

public class ZjcmKkcsModel {
	
	private String []xh;
	private String []xq;
	private String queryequals_bjdm;
	private String queryequals_zydm;
	private String queryequals_xydm;
	private String querylike_xh;
	private String querylike_xn;
	private String queryequals_xn;
	private String []kkcs;
	private String []xn;
	private Pages pages=new Pages();
	public String[] getKkcs() {
		return kkcs;
	}
	public void setKkcs(String[] kkcs) {
		this.kkcs = kkcs;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}
	public String getQueryequals_xn() {
		return queryequals_xn;
	}
	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}
	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}
	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}
	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}
	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}
	public String getQuerylike_xh() {
		return querylike_xh;
	}
	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}
	public String getQuerylike_xn() {
		return querylike_xn;
	}
	public void setQuerylike_xn(String querylike_xn) {
		this.querylike_xn = querylike_xn;
	}
	public String[] getXh() {
		return xh;
	}
	public void setXh(String[] xh) {
		this.xh = xh;
	}
	public String[] getXn() {
		return xn;
	}
	public void setXn(String[] xn) {
		this.xn = xn;
	}
	public String[] getXq() {
		return xq;
	}
	public void setXq(String[] xq) {
		this.xq = xq;
	}
}
