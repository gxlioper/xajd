package xgxt.rcgl.gzdx.fsbtgl;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class FsbtglForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	private Pages pages = new Pages();
	private String yf;     //月份 
	private String jsr;     //经手人 
	private String xh;     //学号 
	private String btdm;     //补贴代码 
	private String ffsj;     //发放时间 
	private String nd;     //年度 
	private String xydm;
	private String zydm;
	private String bjdm;
	private String xm;
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	public String getJsr() {
		return jsr;
	}
	public void setJsr(String jsr) {
		this.jsr = jsr;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getBtdm() {
		return btdm;
	}
	public void setBtdm(String btdm) {
		this.btdm = btdm;
	}
	public String getFfsj() {
		return ffsj;
	}
	public void setFfsj(String ffsj) {
		this.ffsj = ffsj;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
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
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
}
