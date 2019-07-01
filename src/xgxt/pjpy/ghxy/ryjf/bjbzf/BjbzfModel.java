package xgxt.pjpy.ghxy.ryjf.bjbzf;

/**
 * Title: 学生工作管理系统
 * Description:评奖评优荣誉加分Model
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-8-05
 */
public class BjbzfModel {

	private String xh[];     //学号
	private String xm;     //姓名
	private String yd;     //月度
	private String xn;     //学年
	private String xq;     //学期
	private String[] bzdj;     //表彰等级
	private String[] bzf;     //表彰分
	private String njbzrsh;
	private String xxsh;
	private String[] dj;     //表彰等级
	private String[] bl;     //比率
	private String[] jf;     //加分
	private String[] counts; //人数

	public String[] getCounts() {
		return counts;
	}
	public void setCounts(String[] counts) {
		this.counts = counts;
	}
	public String[] getBl() {
		return bl;
	}
	public void setBl(String[] bl) {
		this.bl = bl;
	}
	public String[] getDj() {
		return dj;
	}
	public void setDj(String[] dj) {
		this.dj = dj;
	}
	public String[] getJf() {
		return jf;
	}
	public void setJf(String[] jf) {
		this.jf = jf;
	}
	public String getNjbzrsh() {
		return njbzrsh;
	}
	public void setNjbzrsh(String njbzrsh) {
		this.njbzrsh = njbzrsh;
	}
	public String getXxsh() {
		return xxsh;
	}
	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}
	public String[] getBzdj() {
		return bzdj;
	}
	public void setBzdj(String[] bzdj) {
		this.bzdj = bzdj;
	}
	public String[] getBzf() {
		return bzf;
	}
	public void setBzf(String[] bzf) {
		this.bzf = bzf;
	}
	public String[] getXh() {
		return xh;
	}
	public void setXh(String[] xh) {
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
	public String getYd() {
		return yd;
	}
	public void setYd(String yd) {
		this.yd = yd;
	}
}
