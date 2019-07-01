package xsgzgl.pjpy.general.xmsz.rssz;
/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_人数设置_Model
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

public class XmszRsszModel {
	
	private String[] xh;// 学号
	
	private String[] pkValue;// 主键值
	
	private String xmdm;// 项目代码
	
	private String xmmc;// 项目名称
	
	private String kzfw;// 控制范围

	private String szbl;// 设置比例
	
	private String[] qdrs;// 确定人数
	
	private String xn;// 学年
	
	private String xq;// 学期
	
	private String nd;// 年度

	public String[] getXh() {
		return xh;
	}

	public void setXh(String[] xh) {
		this.xh = xh;
	}

	public String[] getPkValue() {
		return pkValue;
	}

	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getKzfw() {
		return kzfw;
	}

	public void setKzfw(String kzfw) {
		this.kzfw = kzfw;
	}

	public String getSzbl() {
		return szbl;
	}

	public void setSzbl(String szbl) {
		this.szbl = szbl;
	}

	public String[] getQdrs() {
		return qdrs;
	}

	public void setQdrs(String[] qdrs) {
		this.qdrs = qdrs;
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
}
