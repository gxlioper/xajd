package xgxt.wjdc.gdspyp;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class GdspypWjdcForm extends ActionForm{
	
	private Pages pages = new Pages();
	private String xxc;     //选项C
	private String xxb;     //选项B
	private String id;     //题目id
	private String xxa;     //选项A
	private String xh;     //学号
	private String pynr;     //评议内容
	private String zfh;     //最符合选项
	private String zbf;     //最不符选项
	private String fs;     //分数
	private String xydm;
	private String zydm;
	private String bjdm;
	private String nj;
	private String xm;
	
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
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
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXxc() {
		return xxc;
	}
	public void setXxc(String xxc) {
		this.xxc = xxc;
	}
	public String getXxb() {
		return xxb;
	}
	public void setXxb(String xxb) {
		this.xxb = xxb;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXxa() {
		return xxa;
	}
	public void setXxa(String xxa) {
		this.xxa = xxa;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getPynr() {
		return pynr;
	}
	public void setPynr(String pynr) {
		this.pynr = pynr;
	}
	public String getZfh() {
		return zfh;
	}
	public void setZfh(String zfh) {
		this.zfh = zfh;
	}
	public String getZbf() {
		return zbf;
	}
	public void setZbf(String zbf) {
		this.zbf = zbf;
	}
	public String getFs() {
		return fs;
	}
	public void setFs(String fs) {
		this.fs = fs;
	}
}
