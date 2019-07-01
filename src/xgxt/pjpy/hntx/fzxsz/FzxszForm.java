package xgxt.pjpy.hntx.fzxsz;

import xgxt.dtjs.gdby.tygl.BasicExtendForm;
import xgxt.utils.Pages;

/**
 * Title: 学生工作管理系统
 * Description:评奖评优发展性素质Form
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-8-12
 */
public class FzxszForm extends BasicExtendForm{
	
	private static final long serialVersionUID = 3940037930060644579L;

	private Pages pages = new Pages();
	private String[] xmmc;     //项目名称
	private String[] sxf;     //上限分
	private String[] xmdm;	 //项目代码
	private String[] xmjf; 	 //项目加分
	private String[] xh;     //学号
	private String nj;     //年级
	private String zydm;     //专业代码
	private String xm;     //姓名
	private String zymc;     //专业名称
	private String bjdm;     //班级代码
	private String xn;     //学年
	private String xq;     //学期
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	private String xb;     //性别
	private String xydm;     //学院代码
	private String xymc;     //学院名称
	private String bjmc;     //班级名称
	
	public String[] getSxf() {
		return sxf;
	}
	public void setSxf(String[] sxf) {
		this.sxf = sxf;
	}
	public String[] getXmmc() {
		return xmmc;
	}
	public void setXmmc(String[] xmmc) {
		this.xmmc = xmmc;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
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
	public String[] getXmdm() {
		return xmdm;
	}
	public void setXmdm(String[] xmdm) {
		this.xmdm = xmdm;
	}
	public String[] getXmjf() {
		return xmjf;
	}
	public void setXmjf(String[] xmjf) {
		this.xmjf = xmjf;
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
}
