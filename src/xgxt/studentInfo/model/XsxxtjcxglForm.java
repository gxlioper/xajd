package xgxt.studentInfo.model;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class XsxxtjcxglForm extends ActionForm implements Serializable{
	Pages pages = new Pages();
	private String nj;
	private String xydm;
	private String zydm;
	private String bjdm;
	private String xh;
	
	
	
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
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
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}	
}
