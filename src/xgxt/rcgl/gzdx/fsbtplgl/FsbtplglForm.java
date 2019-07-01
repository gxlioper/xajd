package xgxt.rcgl.gzdx.fsbtplgl;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class FsbtplglForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	
	private Pages pages = new Pages();
	private String[] pk_xh;  //学号
	private String yf;     //月份 
	private String jsr;     //经手人 
	private String xueh;     //学号 
	private String btdm;     //补贴代码 
	private String ffsj;     //发放时间 
	private String nd;     //年度 
	private String xydm;
	private String zydm;
	private String bjdm;
	private String xm;
	private String condition;
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String[] getPk_xh() {
		return pk_xh;
	}
	public void setPk_xh(String[] pkXh) {
		pk_xh = pkXh;
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
	public String getXueh() {
		return xueh;
	}
	public void setXueh(String xueh) {
		this.xueh = xueh;
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
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	
}
