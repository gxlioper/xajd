package xgxt.jxgl.jhzy;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

public class JxglJhzyForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1948684890106534275L;

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

	// 连队代码
	private String lddm;

	// 军训年度
	private String jxnd;

	// 组织名称
	private String zzmc;

	private String[] checkVal;

	// 职务
	private String[] zw;

	// 成员姓名
	private String[] cyxm;

	// 成员性别
	private String[] cyxb;

	// 联系电话
	private String[] lxdh;

	// 分页
	Pages pages = new Pages();

	// 开始日期
	private String ksrq;

	// 结束日期
	private String jsrq;

	// 日期
	private String[] rq;

	// 开始时间
	private String[] kssj;

	// 结束时间
	private String[] jssj;

	// 内容安排
	private String[] nr;

	// 地点
	private String[] dd;

	// 组织者
	private String[] zzry;

	// 开始时间时
	private String[] kssjH;

	// 结束时间时
	private String[] jssjH;

	// 开始时间分
	private String[] kssjM;

	// 结束时间分
	private String[] jssjM;

	// 开始时间秒
	private String[] kssjS;

	// 结束时间秒
	private String[] jssjS;

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
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

	public String getJxnd() {
		return jxnd;
	}

	public void setJxnd(String jxnd) {
		this.jxnd = jxnd;
	}

	public String getLddm() {
		return lddm;
	}

	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	public String[] getCyxb() {
		return cyxb;
	}

	public void setCyxb(String[] cyxb) {
		this.cyxb = cyxb;
	}

	public String[] getCyxm() {
		return cyxm;
	}

	public void setCyxm(String[] cyxm) {
		this.cyxm = cyxm;
	}

	public String[] getLxdh() {
		return lxdh;
	}

	public void setLxdh(String[] lxdh) {
		this.lxdh = lxdh;
	}

	public String[] getZw() {
		return zw;
	}

	public void setZw(String[] zw) {
		this.zw = zw;
	}

	public String getJsrq() {
		return jsrq;
	}

	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}

	public String getKsrq() {
		return ksrq;
	}

	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}

	public String[] getDd() {
		return dd;
	}

	public void setDd(String[] dd) {
		this.dd = dd;
	}

	public String[] getJssj() {
		return jssj;
	}

	public void setJssj(String[] jssj) {
		this.jssj = jssj;
	}

	public String[] getKssj() {
		return kssj;
	}

	public void setKssj(String[] kssj) {
		this.kssj = kssj;
	}

	public String[] getNr() {
		return nr;
	}

	public void setNr(String[] nr) {
		this.nr = nr;
	}

	public String[] getRq() {
		return rq;
	}

	public void setRq(String[] rq) {
		this.rq = rq;
	}

	public String[] getZzry() {
		return zzry;
	}

	public void setZzry(String[] zzry) {
		this.zzry = zzry;
	}

	public String[] getJssjH() {
		return jssjH;
	}

	public void setJssjH(String[] jssjH) {
		this.jssjH = jssjH;
	}

	public String[] getJssjM() {
		return jssjM;
	}

	public void setJssjM(String[] jssjM) {
		this.jssjM = jssjM;
	}

	public String[] getJssjS() {
		return jssjS;
	}

	public void setJssjS(String[] jssjS) {
		this.jssjS = jssjS;
	}

	public String[] getKssjH() {
		return kssjH;
	}

	public void setKssjH(String[] kssjH) {
		this.kssjH = kssjH;
	}

	public String[] getKssjM() {
		return kssjM;
	}

	public void setKssjM(String[] kssjM) {
		this.kssjM = kssjM;
	}

	public String[] getKssjS() {
		return kssjS;
	}

	public void setKssjS(String[] kssjS) {
		this.kssjS = kssjS;
	}

	public String getZzmc() {
		return zzmc;
	}

	public void setZzmc(String zzmc) {
		this.zzmc = zzmc;
	}

}