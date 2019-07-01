package xgxt.pjpy.zjcm.zhf;

import xgxt.utils.Pages;

public class ZhfModel {

	private String nj;

	private String nd;

	private String xn;

	private String zydm;

	private String xydm;

	private String bjdm;

	private String xq;

	private String xh;

	private String xm;

	private String xb;

	// 德育分
	private String[] dyf;

	// 智育分
	private String[] zyf;

	// 体育分
	private String[] tyf;

	// 能力分
	private String[] nlf;

	private String dyfbl;

	private String zyfbl;

	private String tyfbl;

	private String nlfbl;

	// 综合分
	private String[] zhf;

	private String[] xhV;

	private String[] xnV;

	private String[] xqV;
	
	// 通用分页
	Pages pages = new Pages();

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String[] getDyf() {
		return dyf;
	}

	public void setDyf(String[] dyf) {
		this.dyf = dyf;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String[] getNlf() {
		return nlf;
	}

	public void setNlf(String[] nlf) {
		this.nlf = nlf;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public String[] getTyf() {
		return tyf;
	}

	public void setTyf(String[] tyf) {
		this.tyf = tyf;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
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

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String[] getZhf() {
		return zhf;
	}

	public void setZhf(String[] zhf) {
		this.zhf = zhf;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String[] getZyf() {
		return zyf;
	}

	public void setZyf(String[] zyf) {
		this.zyf = zyf;
	}

	public String[] getXhV() {
		return xhV;
	}

	public void setXhV(String[] xhV) {
		this.xhV = xhV;
	}

	public String[] getXnV() {
		return xnV;
	}

	public void setXnV(String[] xnV) {
		this.xnV = xnV;
	}

	public String[] getXqV() {
		return xqV;
	}

	public void setXqV(String[] xqV) {
		this.xqV = xqV;
	}

	public String getDyfbl() {
		return dyfbl;
	}

	public void setDyfbl(String dyfbl) {
		this.dyfbl = dyfbl;
	}

	public String getNlfbl() {
		return nlfbl;
	}

	public void setNlfbl(String nlfbl) {
		this.nlfbl = nlfbl;
	}

	public String getTyfbl() {
		return tyfbl;
	}

	public void setTyfbl(String tyfbl) {
		this.tyfbl = tyfbl;
	}

	public String getZyfbl() {
		return zyfbl;
	}

	public void setZyfbl(String zyfbl) {
		this.zyfbl = zyfbl;
	}

}
