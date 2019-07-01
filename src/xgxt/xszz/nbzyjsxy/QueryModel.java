
package xgxt.xszz.nbzyjsxy;

import java.io.Serializable;

import xgxt.action.Base;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 宁波职业技术学院查询MODEL</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-07-10</p>
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
	private String xn;//学年
	private String nd;//年度
	private String nj;//年级
	private String xm;//姓名
	private String dw;//单位
	private String bm;//部门
	private String sfzh;//身份证号
	private String sj;//手机
	private String jsdm;//角色代码
	private String xhyhm;//学号或用户名
	private String dwlxdh;//单位联系电话
	private String bjrsh;//保荐人审核
	private String xysh;//学院审核
	private String xxsh;//学校审核
	private String sfjk;//是否捐款
	private String go;//查询标识
	private String tjlx;//统计类型
	private String isQuery;//功能标识
	
	public String getTjlx() {
		return tjlx;
	}
	public void setTjlx(String tjlx) {
		this.tjlx = tjlx;
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
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = Base.chgNull(xxsh,"",1);
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = Base.chgNull(xysh,"",1);
	}
	public String getDwlxdh() {
		return dwlxdh;
	}
	public void setDwlxdh(String dwlxdh) {
		this.dwlxdh = dwlxdh;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getSj() {
		return sj;
	}
	public void setSj(String sj) {
		this.sj = sj;
	}
	public String getBjrsh() {
		return bjrsh;
	}
	public void setBjrsh(String bjrsh) {
		this.bjrsh = Base.chgNull(bjrsh,"",1);
	}
	public String getJsdm() {
		return jsdm;
	}
	public void setJsdm(String jsdm) {
		this.jsdm = jsdm;
	}
	public String getXhyhm() {
		return xhyhm;
	}
	public void setXhyhm(String xhyhm) {
		this.xhyhm = xhyhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public String getSfjk() {
		return sfjk;
	}
	public void setSfjk(String sfjk) {
		this.sfjk = sfjk;
	}
}
