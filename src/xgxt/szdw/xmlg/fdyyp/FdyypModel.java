package xgxt.szdw.xmlg.fdyyp;

import xgxt.utils.Pages;

public class FdyypModel {

	// 学号
	private String xh;

	// 姓名
	private String xm;

	// 月份
	private String yf;

	// 教师代码
	private String jsdm;

	// 辅导员职工号
	private String zgh;

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

	// 汇总学年
	private String hzxn;

	// 汇总学期
	private String hzxq;

	// 汇总开始时间
	private String kssj;

	// 汇总结束时间
	private String jssj;

	// 部门代码
	private String bmdm;

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

	// 学院审核意见
	private String xyyj;

	// 学校审核意见
	private String xxyj;

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

	// 测评得分
	private String cpdf;

	private String gsnr;// '过失内容';

	private String gssj;// '过失时间';

	private String bz;// '备注';

	private String lrr;// '录入人';

	private String lrsj;// '录入时间';

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

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getJsdm() {
		return jsdm;
	}

	public void setJsdm(String jsdm) {
		this.jsdm = jsdm;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getCpdf() {
		return cpdf;
	}

	public void setCpdf(String cpdf) {
		this.cpdf = cpdf;
	}

	public String getYf() {
		return yf;
	}

	public void setYf(String yf) {
		this.yf = yf;
	}

	public String getXxyj() {
		return xxyj;
	}

	public void setXxyj(String xxyj) {
		this.xxyj = xxyj;
	}

	public String getXyyj() {
		return xyyj;
	}

	public void setXyyj(String xyyj) {
		this.xyyj = xyyj;
	}

	public String getHzxn() {
		return hzxn;
	}

	public void setHzxn(String hzxn) {
		this.hzxn = hzxn;
	}

	public String getHzxq() {
		return hzxq;
	}

	public void setHzxq(String hzxq) {
		this.hzxq = hzxq;
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

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getGsnr() {
		return gsnr;
	}

	public void setGsnr(String gsnr) {
		this.gsnr = gsnr;
	}

	public String getGssj() {
		return gssj;
	}

	public void setGssj(String gssj) {
		this.gssj = gssj;
	}

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

}
