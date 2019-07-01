/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-6-1 下午02:24:44</p>
 */
package xgxt.xsgygl.whlghxxy;

import org.apache.struts.action.ActionForm;

public class GyglWhlghxxyForm extends ActionForm {

	private static final long serialVersionUID = 5454241113885548084L;
	private String nj;
	private String zs;
	private String xn;
	private String xq;
	private String dqnd;		//当前年度
	private String lddm;		//楼栋代码
	private String yf;			//月份
	private String lc;
	private String qsh;
	private String[] jcz;
	private String[] jcsj;
	private String[] dj;
	private String[] fs;
	private String[] jcbm;
	private String[] ssbhv;
	public String[] getSsbhv() {
		return ssbhv;
	}
	public void setSsbhv(String[] ssbhv) {
		this.ssbhv = ssbhv;
	}
	public String[] getJcbm() {
		return jcbm;
	}
	public void setJcbm(String[] jcbm) {
		this.jcbm = jcbm;
	}
	public String getLc() {
		return lc;
	}
	public void setLc(String lc) {
		this.lc = lc;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getZs() {
		return zs;
	}
	public void setZs(String zs) {
		this.zs = zs;
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
	public String getDqnd() {
		return dqnd;
	}
	public void setDqnd(String dqnd) {
		this.dqnd = dqnd;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getYf() {
		return yf;
	}
	public void setYf(String yf) {
		this.yf = yf;
	}
	public String[] getDj() {
		return dj;
	}
	public void setDj(String[] dj) {
		this.dj = dj;
	}
	public String[] getFs() {
		return fs;
	}
	public void setFs(String[] fs) {
		this.fs = fs;
	}
	public String[] getJcz() {
		return jcz;
	}
	public void setJcz(String[] jcz) {
		this.jcz = jcz;
	}
	public String[] getJcsj() {
		return jcsj;
	}
	public void setJcsj(String[] jcsj) {
		this.jcsj = jcsj;
	}
}
