
package xgxt.xszz.zgdzdx;

import java.io.Serializable;

import xgxt.utils.Pages;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 中国地质大学查询MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-09</p>
 */
public class QueryModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bjdm;//班级代码
	private String xydm;//学院代码
	private String zydm;//专业代码
	private String xh;//学号
	private String xq;//学期
	private String xn;//学年
	private String nd;//年度
	private String nj;//年级
	private String tjdc;//推荐档次
	private String xysh;//学院审核
	private String xxsh;//学校审核
	private String hth;//合同号
	private String sjfw1;//时间范围
	private String sjfw2;//时间范围
	private String shjg;//审核结果
	private String xm;
	private String sfzh;
	private String xz;//还款协议还是展期后还款协议
	private String hkxyqssj;//还款协议签署时间
	private String go;//查询标识
	private String isQuery;//功能标识
	Pages pages = new Pages();
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getGo() {
		return go;
	}
	public void setGo(String go) {
		this.go = go;
	}
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
	public String getIsQuery() {
		return isQuery;
	}
	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}
	public String getTjdc() {
		return tjdc;
	}
	public void setTjdc(String tjdc) {
		this.tjdc = tjdc;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = xysh;
	}
	public String getHth() {
		return hth;
	}
	public void setHth(String hth) {
		this.hth = hth;
	}
	public String getSjfw1() {
		return sjfw1;
	}
	public void setSjfw1(String sjfw1) {
		this.sjfw1 = sjfw1;
	}
	public String getSjfw2() {
		return sjfw2;
	}
	public void setSjfw2(String sjfw2) {
		this.sjfw2 = sjfw2;
	}
	public String getShjg() {
		return shjg;
	}
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String getXz() {
		return xz;
	}
	public void setXz(String xz) {
		this.xz = xz;
	}
	public String getHkxyqssj() {
		return hkxyqssj;
	}
	public void setHkxyqssj(String hkxyqssj) {
		this.hkxyqssj = hkxyqssj;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
}
