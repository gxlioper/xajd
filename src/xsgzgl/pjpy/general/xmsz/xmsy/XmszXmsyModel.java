package xsgzgl.pjpy.general.xmsz.xmsy;
/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_项目顺延_Model
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

public class XmszXmsyModel {
	
	private String[] xh;// 学号
	
	private String xmdm;// 项目代码
	
	private String xmmc;// 项目名称

	private String xn;// 学年
	
	private String xq;// 学期
	
	private String nd;// 年度

	public String[] getXh() {
		return xh;
	}

	public void setXh(String[] xh) {
		this.xh = xh;
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
