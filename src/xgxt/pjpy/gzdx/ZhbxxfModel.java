/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-12-25 上午08:54:58</p>
 */
package xgxt.pjpy.gzdx;

import xgxt.utils.Pages;

public class ZhbxxfModel {
    private String[] pllb;
    private String[] pldm;
    private String[] plnr;
    private String xh;
    private String xn;
    private String nj;
    private String xydm;
    private String xm;
    private String zydm;
    private String bjdm;
	private String[] fs;
	private String[] fslb;
    Pages pages = new Pages();
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
	public String[] getPldm() {
		return pldm;
	}
	public void setPldm(String[] pldm) {
		this.pldm = pldm;
	}
	public String[] getPllb() {
		return pllb;
	}
	public void setPllb(String[] pllb) {
		this.pllb = pllb;
	}
	public String[] getPlnr() {
		return plnr;
	}
	public void setPlnr(String[] plnr) {
		this.plnr = plnr;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String[] getFs() {
		return fs;
	}
	public void setFs(String[] fs) {
		this.fs = fs;
	}
	public String[] getFslb() {
		return fslb;
	}
	public void setFslb(String[] fslb) {
		this.fslb = fslb;
	}
}
