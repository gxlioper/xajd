package xgxt.xszz.jhzyjsxy;

import java.io.Serializable;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 金华职业技术学院帮困助学基金表MODEL
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 周觅
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2009-09-27
 * </p>
 */
public class BkzxjjModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1335094044111800053L;

	/**
	 * 
	 */

	private String nd;// 年度

	private String xh;// 学号

	private String xm;// 姓名

	private String xb;// 性别
	
	private String nj;// 年级
	
	private String rxrq;// 入学日期
	
	private String sfzh;// 身份证号

	private String zzmmmc;
	private String mzmc;
	private String xymc;
	private String zymc;
	private String bjmc;
	private String csrq;
	
	private String jtzzjyb ;// 家庭住址及邮编

	private String jtzz;// 家庭地址

	private String jtyb;// 家庭邮编
	
	private String jg;// 籍贯

	private String jtnjjcsr;// 家庭年经济纯收入

	private String jtrks;// 家庭人口数

	private String xxztqk;// 学习总体情况

	private String dy;// 德育

	private String cshzjl;// 曾受何种奖励

	private String xfjnqk;// 学费交纳情况

	private String cshzwjcf;// 曾受何种违纪处分

	private String zxdkjje;// 助学贷款及金额

	private String gjxjxjje;// 获国家、校奖学金金额

	private String gjlzzxjjje;// 获国家励志、助学金及金额

	private String bxnhknbzjje;// 本学年获困难补助及金额

	private String sqly;// 申请理由

	private String sqsj;// 申请时间

	private String zxjjdm;// 助学基金资助代码

	private String zxjjdj;// 助学基金资助等级

	private String zxjjje;// 助学基金资助金额

	private String fdysh;// 辅导员审核

	private String fdyshsj;// 辅导员审核时间

	private String fdyshyj;// 辅导员审核意见

	private String xysh;// 学院审核

	private String xyshsj;// 学院审核时间

	private String xyshyj;// 学院审核意见

	private String xxsh;// 学校审核

	private String xxshsj;// 学校审核时间

	private String xxshyj;// 学校审核意见

	public String getBxnhknbzjje() {
		return bxnhknbzjje;
	}

	public void setBxnhknbzjje(String bxnhknbzjje) {
		this.bxnhknbzjje = bxnhknbzjje;
	}

	public String getCshzjl() {
		return cshzjl;
	}

	public void setCshzjl(String cshzjl) {
		this.cshzjl = cshzjl;
	}

	public String getCshzwjcf() {
		return cshzwjcf;
	}

	public void setCshzwjcf(String cshzwjcf) {
		this.cshzwjcf = cshzwjcf;
	}

	public String getDy() {
		return dy;
	}

	public void setDy(String dy) {
		this.dy = dy;
	}

	public String getFdysh() {
		return fdysh;
	}

	public void setFdysh(String fdysh) {
		this.fdysh = fdysh;
	}

	public String getFdyshsj() {
		return fdyshsj;
	}

	public void setFdyshsj(String fdyshsj) {
		this.fdyshsj = fdyshsj;
	}

	public String getFdyshyj() {
		return fdyshyj;
	}

	public void setFdyshyj(String fdyshyj) {
		this.fdyshyj = fdyshyj;
	}

	public String getGjlzzxjjje() {
		return gjlzzxjjje;
	}

	public void setGjlzzxjjje(String gjlzzxjjje) {
		this.gjlzzxjjje = gjlzzxjjje;
	}

	public String getGjxjxjje() {
		return gjxjxjje;
	}

	public void setGjxjxjje(String gjxjxjje) {
		this.gjxjxjje = gjxjxjje;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getJtnjjcsr() {
		return jtnjjcsr;
	}

	public void setJtnjjcsr(String jtnjjcsr) {
		this.jtnjjcsr = jtnjjcsr;
	}

	public String getJtrks() {
		return jtrks;
	}

	public void setJtrks(String jtrks) {
		this.jtrks = jtrks;
	}

	public String getJtzzjyb() {
		return jtzzjyb;
	}

	public void setJtzzjyb(String jtzzjyb) {
		this.jtzzjyb = jtzzjyb;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getSqly() {
		return sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getSqsj() {
		return sqsj;
	}

	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}

	public String getXfjnqk() {
		return xfjnqk;
	}

	public void setXfjnqk(String xfjnqk) {
		this.xfjnqk = xfjnqk;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXxsh() {
		return xxsh;
	}

	public void setXxsh(String xxsh) {
		this.xxsh = xxsh;
	}

	public String getXxshsj() {
		return xxshsj;
	}

	public void setXxshsj(String xxshsj) {
		this.xxshsj = xxshsj;
	}

	public String getXxshyj() {
		return xxshyj;
	}

	public void setXxshyj(String xxshyj) {
		this.xxshyj = xxshyj;
	}

	public String getXxztqk() {
		return xxztqk;
	}

	public void setXxztqk(String xxztqk) {
		this.xxztqk = xxztqk;
	}

	public String getXysh() {
		return xysh;
	}

	public void setXysh(String xysh) {
		this.xysh = xysh;
	}

	public String getXyshsj() {
		return xyshsj;
	}

	public void setXyshsj(String xyshsj) {
		this.xyshsj = xyshsj;
	}

	public String getXyshyj() {
		return xyshyj;
	}

	public void setXyshyj(String xyshyj) {
		this.xyshyj = xyshyj;
	}

	public String getZxdkjje() {
		return zxdkjje;
	}

	public void setZxdkjje(String zxdkjje) {
		this.zxdkjje = zxdkjje;
	}

	public String getZxjjdj() {
		return zxjjdj;
	}

	public void setZxjjdj(String zxjjdj) {
		this.zxjjdj = zxjjdj;
	}

	public String getZxjjdm() {
		return zxjjdm;
	}

	public void setZxjjdm(String zxjjdm) {
		this.zxjjdm = zxjjdm;
	}

	public String getZxjjje() {
		return zxjjje;
	}

	public void setZxjjje(String zxjjje) {
		this.zxjjje = zxjjje;
	}

	public String getJtyb() {
		return jtyb;
	}

	public void setJtyb(String jtyb) {
		this.jtyb = jtyb;
	}

	public String getJtzz() {
		return jtzz;
	}

	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
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

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getCsrq() {
		return csrq;
	}

	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}

	public String getMzmc() {
		return mzmc;
	}

	public void setMzmc(String mzmc) {
		this.mzmc = mzmc;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getZzmmmc() {
		return zzmmmc;
	}

	public void setZzmmmc(String zzmmmc) {
		this.zzmmmc = zzmmmc;
	}

	public String getRxrq() {
		return rxrq;
	}

	public void setRxrq(String rxrq) {
		this.rxrq = rxrq;
	}

	

}
