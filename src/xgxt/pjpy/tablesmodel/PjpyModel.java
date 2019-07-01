
package xgxt.pjpy.tablesmodel;

import xgxt.utils.Pages;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 评奖评优Model公用类</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-10</p>
 */
public class PjpyModel {
	private String userName;
	private String userType;
	private String isFdy;
	public String xh;//学号
	public String xm;//姓名
	public String xb;//性别
	public String bjdm;//班级代码
	public String zydm;//专业代码
	public String xydm;//学院代码
	public String bjmc;//班级名称
	public String zymc;//专业名称
	public String xymc;//学院名称
	public String nj;//年级
	public String jxjdm;//奖学金代码
	public String rychdm;//荣誉称号代码
	public String xn;//学年
	public String nd;//年度
	public String xq;//学期
	public String xqmc;
	public String drzw;
	public String fdysh;
	public String xysh;
	public String xxsh;
	public String pkValue;
	private String sh;
	private String yj;
	
	private String save_xh;
	private String save_xn;
	private String save_xq;
	private String save_jxjdm;
	private String save_nd;
	private String save_rychdm;
	private String queryequals_xydm;
	private String queryequals_zydm;
	private String queryequals_bjdm;
	private String queryequils_xb;
	private String queryequals_nj;
	private String queryequals_xn;
	private String queryequals_nd;
	private String querylike_xh;
	private String querylike_xm;
	private String queryequals_xq;
	
	
	Pages pages = new Pages();	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getIsFdy() {
		return isFdy;
	}
	public void setIsFdy(String isFdy) {
		this.isFdy = isFdy;
	}
	public String getSh() {
		return sh;
	}
	public void setSh(String sh) {
		this.sh = sh;
	}
	public String getYj() {
		return yj;
	}
	public void setYj(String yj) {
		this.yj = yj;
	}
	public String getDrzw() {
		return drzw;
	}
	public void setDrzw(String drzw) {
		this.drzw = drzw;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
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
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
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
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getBjmc() {
		return bjmc;
	}
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	public String getJxjdm() {
		return jxjdm;
	}
	public void setJxjdm(String jxjdm) {
		this.jxjdm = jxjdm;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getRychdm() {
		return rychdm;
	}
	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
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
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	public String getZydm() {
		return zydm;
	}
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	public String getZymc() {
		return zymc;
	}
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getQueryequals_bjdm() {
		return queryequals_bjdm;
	}
	public void setQueryequals_bjdm(String queryequals_bjdm) {
		this.queryequals_bjdm = queryequals_bjdm;
	}
	public String getQueryequals_nd() {
		return queryequals_nd;
	}
	public void setQueryequals_nd(String queryequals_nd) {
		this.queryequals_nd = queryequals_nd;
	}
	public String getQueryequals_nj() {
		return queryequals_nj;
	}
	public void setQueryequals_nj(String queryequals_nj) {
		this.queryequals_nj = queryequals_nj;
	}
	public String getQueryequals_xn() {
		return queryequals_xn;
	}
	public void setQueryequals_xn(String queryequals_xn) {
		this.queryequals_xn = queryequals_xn;
	}
	public String getQueryequals_xq() {
		return queryequals_xq;
	}
	public void setQueryequals_xq(String queryequals_xq) {
		this.queryequals_xq = queryequals_xq;
	}
	public String getQueryequals_xydm() {
		return queryequals_xydm;
	}
	public void setQueryequals_xydm(String queryequals_xydm) {
		this.queryequals_xydm = queryequals_xydm;
	}
	public String getQueryequals_zydm() {
		return queryequals_zydm;
	}
	public void setQueryequals_zydm(String queryequals_zydm) {
		this.queryequals_zydm = queryequals_zydm;
	}
	public String getQueryequils_xb() {
		return queryequils_xb;
	}
	public void setQueryequils_xb(String queryequils_xb) {
		this.queryequils_xb = queryequils_xb;
	}
	public String getQuerylike_xh() {
		return querylike_xh;
	}
	public void setQuerylike_xh(String querylike_xh) {
		this.querylike_xh = querylike_xh;
	}
	public String getQuerylike_xm() {
		return querylike_xm;
	}
	public void setQuerylike_xm(String querylike_xm) {
		this.querylike_xm = querylike_xm;
	}
	public String getSave_jxjdm() {
		return save_jxjdm;
	}
	public void setSave_jxjdm(String save_jxjdm) {
		this.save_jxjdm = save_jxjdm;
	}
	public String getSave_nd() {
		return save_nd;
	}
	public void setSave_nd(String save_nd) {
		this.save_nd = save_nd;
	}
	public String getSave_rychdm() {
		return save_rychdm;
	}
	public void setSave_rychdm(String save_rychdm) {
		this.save_rychdm = save_rychdm;
	}
	public String getSave_xh() {
		return save_xh;
	}
	public void setSave_xh(String save_xh) {
		this.save_xh = save_xh;
	}
	public String getSave_xn() {
		return save_xn;
	}
	public void setSave_xn(String save_xn) {
		this.save_xn = save_xn;
	}
	public String getSave_xq() {
		return save_xq;
	}
	public void setSave_xq(String save_xq) {
		this.save_xq = save_xq;
	}
	public String getFdysh() {
		return fdysh;
	}
	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
}
