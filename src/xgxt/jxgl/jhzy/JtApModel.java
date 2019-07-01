/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-9-27 上午11:12:23</p>
 */
package xgxt.jxgl.jhzy;

public class JtApModel {
	private String xydm;
	private String xn;
	private String xq;
	private String nd;
	//日期
	private String[] rq;

	//开始时间
	private String[] kssj;

	//结束时间
	private String[] jssj;

//	内容安排
	private String[] nr;

	//	地点
	private String[] dd;

	//	组织者
	private String[] zzry;

//	开始时间时
	private String[] kssjH;

	//结束时间时
	private String[] jssjH;

//	开始时间分
	private String[] kssjM;

	//结束时间分
	private String[] jssjM;

//	开始时间秒
	private String[] kssjS;

	//结束时间秒
	private String[] jssjS;

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

	public String[] getKssj() {
		return kssj;
	}

	public void setKssj(String[] kssj) {
		this.kssj = kssj;
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

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
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

	public String[] getZzry() {
		return zzry;
	}

	public void setZzry(String[] zzry) {
		this.zzry = zzry;
	}
}
