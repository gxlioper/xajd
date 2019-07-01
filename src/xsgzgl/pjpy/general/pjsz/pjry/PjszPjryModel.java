package xsgzgl.pjpy.general.pjsz.pjry;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_评奖人员_Model
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

public class PjszPjryModel {

	private String[] xh;// 学号

	private String bjdm;// 评奖班级代码
	
	private String bjmc;// 评奖班级名称
	
	private String sfcp;// 是否参评
	
	public String[] getXh() {
		return xh;
	}

	public void setXh(String[] xh) {
		this.xh = xh;
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

	public String getSfcp() {
		return sfcp;
	}

	public void setSfcp(String sfcp) {
		this.sfcp = sfcp;
	}
}
