
package xgxt.xszz.commonN05;

import java.io.Serializable;

import xgxt.base.DealString;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: N05查询MODEL</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-10-13</p>
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
	private String xq;//学期
	private String xm;
	private String xb;
	private String sfzh;
	private String fdysh;
	private String xysh;
	private String xxsh;
	private String zxjdj;
	private String xmb;
	private String xmdm;
	private String txsj1;
	private String txsj2;
	private String qxfqj;
	private String go;//查询标识
	private String isQuery;//功能标识
	private String userType;
	
	public String getQxfqj() {
		return qxfqj;
	}
	public void setQxfqj(String qxfqj) {
		this.qxfqj = qxfqj;
	}
	public String getTxsj1() {
		return txsj1;
	}
	public void setTxsj1(String txsj1) {
		this.txsj1 = txsj1;
	}
	public String getTxsj2() {
		return txsj2;
	}
	public void setTxsj2(String txsj2) {
		this.txsj2 = txsj2;
	}
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXmb() {
		return xmb;
	}
	public void setXmb(String xmb) {
		this.xmb = xmb;
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
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = DealString.toGBK(xb);
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = DealString.toGBK(xm);
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = DealString.toGBK(xxsh);
	}
	public String getXysh() {
		return xysh;
	}
	public void setXysh(String xysh) {
		this.xysh = DealString.toGBK(xysh);
	}
	public String getZxjdj() {
		return zxjdj;
	}
	public void setZxjdj(String zxjdj) {
		this.zxjdj = DealString.toGBK(zxjdj);
	}
	public String getFdysh() {
		return fdysh;
	}
	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
