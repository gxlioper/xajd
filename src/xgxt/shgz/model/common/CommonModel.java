
package xgxt.shgz.model.common;

import xgxt.utils.Pages;

public class CommonModel {
	
	/**
	* <p>Title: 学工管理系统</p>
	* <p>Description: 学生信息管理思政队伍-基础model类</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author 鲁宁
	* @version 1.0
	*/
	Pages pages = new Pages();
	
	private String xydm;
	private String zydm;
	private String bjdm;
	private String xn;
	private String nd;
	private String xq;
	private String xm;
	private String nj;
	private String stdm;
	private String xh;
	private String shzt;
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
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
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
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
	public String getStdm() {
		return stdm;
	}
	public void setStdm(String stdm) {
		this.stdm = stdm;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}

}
