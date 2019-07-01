package xgxt.studentInfo.model;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

@SuppressWarnings("serial")
public class XsxxflForm extends ActionForm implements Serializable{
	Pages pages = new Pages();
	private String xh;
	private String xydm;
	private String zydm;
	private String bjdm;
	private String nj;
	private String queryequals_zrxydm;
	private String queryequals_zrzydm;
	private String queryequals_zrbjdm;
	private String queryequals_zrnj;
	private String queryequals_ysbjdm;
	private String queryequals_mkbjdm;
	private String querylike_xh;
	private String querylike_xm;
	private String zrbjdm;//自然班级代码

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}
	
	public String getQueryequals_zrnj() {
		return queryequals_zrnj;
	}

	public void setQueryequals_zrnj(String queryequals_zrnj) {
		this.queryequals_zrnj = queryequals_zrnj;
	}

	public String getQueryequals_ysbjdm() {
		return queryequals_ysbjdm;
	}

	public void setQueryequals_ysbjdm(String queryequals_ysbjdm) {
		this.queryequals_ysbjdm = queryequals_ysbjdm;
	}

	public String getQueryequals_mkbjdm() {
		return queryequals_mkbjdm;
	}

	public void setQueryequals_mkbjdm(String queryequals_mkbjdm) {
		this.queryequals_mkbjdm = queryequals_mkbjdm;
	}

	public String getQuerylike_xh() {
		return querylike_xh;
	}

	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}

	public String getQuerylike_xm() {
		return querylike_xm;
	}

	public void setQuerylike_xm(String querylike_xm) {
		this.querylike_xm = querylike_xm;
	}

	public String getQueryequals_zrxydm() {
		return queryequals_zrxydm;
	}

	public void setQueryequals_zrxydm(String queryequals_zrxydm) {
		this.queryequals_zrxydm = queryequals_zrxydm;
	}

	public String getQueryequals_zrzydm() {
		return queryequals_zrzydm;
	}

	public void setQueryequals_zrzydm(String queryequals_zrzydm) {
		this.queryequals_zrzydm = queryequals_zrzydm;
	}

	public String getQueryequals_zrbjdm() {
		return queryequals_zrbjdm;
	}

	public void setQueryequals_zrbjdm(String queryequals_zrbjdm) {
		this.queryequals_zrbjdm = queryequals_zrbjdm;
	}

	public String getZrbjdm() {
		return zrbjdm;
	}

	public void setZrbjdm(String zrbjdm) {
		this.zrbjdm = zrbjdm;
	}	
}
