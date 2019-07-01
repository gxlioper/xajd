package xgxt.dtjs.zgdzdx.wlxx;

import xgxt.utils.Pages;

public class WlxxModel {

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

	// 值班ID
	private String zbid;

	// 值班时间
	private String zbsj;

	// 开始时间
	private String kssj;

	// 结束时间
	private String jssj;

	// 值班情况
	private String zbqk;

	// 备注
	private String wlbz;

	// 通用分页
	Pages pages = new Pages();

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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

	public String getWlbz() {
		return wlbz;
	}

	public void setWlbz(String wlbz) {
		this.wlbz = wlbz;
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

	public String getZbid() {
		return zbid;
	}

	public void setZbid(String zbid) {
		this.zbid = zbid;
	}

	public String getZbqk() {
		return zbqk;
	}

	public void setZbqk(String zbqk) {
		this.zbqk = zbqk;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String getZbsj() {
		return zbsj;
	}

	public void setZbsj(String zbsj) {
		this.zbsj = zbsj;
	}

}
