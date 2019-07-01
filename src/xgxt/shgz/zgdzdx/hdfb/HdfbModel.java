package xgxt.shgz.zgdzdx.hdfb;

import xgxt.utils.Pages;

public class HdfbModel {

	// 学号
	private String xh;

	// 姓名
	private String xm;

	// 性别
	private String xb;

	// 年级
	private String nj;

	// 学院代码
	private String xydm;

	// 专业代码
	private String zydm;

	// 班级代码
	private String bjdm;

	// 学年
	private String xn;

	// 年度
	private String nd;

	// 学期
	private String xq;

	// 活动代码
	private String hddm;

	// 开始时间
	private String kssj;

	// 结束时间
	private String jssj;

	// 是否需要学院审核
	private String isxysh;

	// 通用分页
	Pages pages = new Pages();

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getHddm() {
		return hddm;
	}

	public void setHddm(String hddm) {
		this.hddm = hddm;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
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

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
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

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getIsxysh() {
		return isxysh;
	}

	public void setIsxysh(String isxysh) {
		this.isxysh = isxysh;
	}

}
