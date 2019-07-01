package xsgzgl.pjpy.general.zhcp;
/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_综合测评_综测信息_Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyZhcpModel {
	
	private String xmdm;// 项目代码
	
	private String xmmc;// 项目名称

	private String xn;// 学年
	
	private String xq;// 学期
	
	private String nd;// 年度
	
	private String lyb;//来源表
	
	private String xmjb;//项目级别
	
	private String[]xszdArr;//综测查询列选信息
	
	private String[] xh;// 学号
	
	private String xydm;
	
	private String zydm;
	
	private String bjdm;
	
	private String nj;

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

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	
	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
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

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String[] getXh() {
		return xh;
	}

	public void setXh(String[] xh) {
		this.xh = xh;
	}

	public String getLyb() {
		return lyb;
	}

	public void setLyb(String lyb) {
		this.lyb = lyb;
	}

	public String getXmjb() {
		return xmjb;
	}

	public void setXmjb(String xmjb) {
		this.xmjb = xmjb;
	}

	public String[] getXszdArr() {
		return xszdArr;
	}

	public void setXszdArr(String[] xszdArr) {
		this.xszdArr = xszdArr;
	}
}
