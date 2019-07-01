package xgxt.szdw.xmlg.wmbj;

import xgxt.utils.Pages;

public class WmbjModel {

	// 学号
	private String xh;

	// 姓名
	private String xm;

	// 申报人学号
	private String sbr;

	// 申报人姓名
	private String sbrxm;

	// 申报时间
	private String sbsj;

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

	// 字段
	private String zd;

	// 字段名
	private String zdm;

	// 字段
	private String[] arrZd;

	// 字段名
	private String[] arrZdz;

	// 字段类型
	private String zdlx;

	// 通用分页
	Pages pages = new Pages();

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getZdlx() {
		return zdlx;
	}

	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String[] getArrZd() {
		return arrZd;
	}

	public void setArrZd(String[] arrZd) {
		this.arrZd = arrZd;
	}

	public String[] getArrZdz() {
		return arrZdz;
	}

	public void setArrZdz(String[] arrZdz) {
		this.arrZdz = arrZdz;
	}

	public String getSbr() {
		return sbr;
	}

	public void setSbr(String sbr) {
		this.sbr = sbr;
	}

	public String getSbrxm() {
		return sbrxm;
	}

	public void setSbrxm(String sbrxm) {
		this.sbrxm = sbrxm;
	}

	public String getSbsj() {
		return sbsj;
	}

	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}

}
