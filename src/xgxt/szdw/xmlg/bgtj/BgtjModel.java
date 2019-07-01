package xgxt.szdw.xmlg.bgtj;

import xgxt.utils.Pages;

public class BgtjModel {

	private String bgid;// '报告ID';

	private String tjr;// '提交人';

	private String bgdm;// '报告代码';

	private String bgnr;// '报告内容';

	private String tjsj;// '提交时间';

	private String lx;// '类型';
	
	private String ydbf;// '应对办法';
	
	private String bz;// '备注';
	
	private String xmmc;// '项目名称';
	
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

	// 通用分页
	Pages pages = new Pages();

	public String getBgdm() {
		return bgdm;
	}

	public void setBgdm(String bgdm) {
		this.bgdm = bgdm;
	}

	public String getBgid() {
		return bgid;
	}

	public void setBgid(String bgid) {
		this.bgid = bgid;
	}

	public String getBgnr() {
		return bgnr;
	}

	public void setBgnr(String bgnr) {
		this.bgnr = bgnr;
	}

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

	public String getTjr() {
		return tjr;
	}

	public void setTjr(String tjr) {
		this.tjr = tjr;
	}

	public String getTjsj() {
		return tjsj;
	}

	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
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

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getYdbf() {
		return ydbf;
	}

	public void setYdbf(String ydbf) {
		this.ydbf = ydbf;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

}
