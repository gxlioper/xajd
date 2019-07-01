package xsgzgl.pjpy.general.pjsz.zcxm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_综测项目_Model
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

public class PjszZcxmModel {

	private String xn = "无";// 学年

	private String xq = "无";// 学期

	private String nd = "无";// 年度

	private String xmdm;// 项目代码

	private String xmmc;// 项目名称

	private String xmjb;// 项目级别

	private String sjdm;// 上级代码

	private String jjf;// 加减分

	private String lrly;// 录入理由

	private String[] bldm;// 比例代码
	
	private String[] bl;// 比例
	
	public String[] getBldm() {
		return bldm;
	}

	public void setBldm(String[] bldm) {
		this.bldm = bldm;
	}

	public String[] getBl() {
		return bl;
	}

	public void setBl(String[] bl) {
		this.bl = bl;
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

	public String getXmjb() {
		return xmjb;
	}

	public void setXmjb(String xmjb) {
		this.xmjb = xmjb;
	}

	public String getSjdm() {
		return sjdm;
	}

	public void setSjdm(String sjdm) {
		this.sjdm = sjdm;
	}

	public String getJjf() {
		return jjf;
	}

	public void setJjf(String jjf) {
		this.jjf = jjf;
	}

	public String getLrly() {
		return lrly;
	}

	public void setLrly(String lrly) {
		this.lrly = lrly;
	}
}
