package xgxt.pjpy.zjcm.cprs;

import xgxt.utils.Pages;

public class ZjcmCprsModel {
	
	private String []bjdm;
	private String []rs;
	private String []xn;
	private String queryequals_xn;
	private String queryequals_bjdm;
	private String queryequals_zydm;
	private String queryequals_xydm;
	private String queryequals_nj;
	private Pages pages=new Pages();
	public String[] getBjdm() {
		return bjdm;
	}
	public void setBjdm(String[] bjdm) {
		this.bjdm = bjdm;
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
	public String getQueryequals_nj() {
		return queryequals_nj;
	}
	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
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
	public String[] getRs() {
		return rs;
	}
	public void setRs(String[] rs) {
		this.rs = rs;
	}
	public String[] getXn() {
		return xn;
	}
	public void setXn(String[] xn) {
		this.xn = xn;
	}
}
