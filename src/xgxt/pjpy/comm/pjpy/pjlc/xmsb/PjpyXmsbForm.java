package xgxt.pjpy.comm.pjpy.pjlc.xmsb;

import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.pjlc.PjpyPjlcForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖流程_项目上报_Form类
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

public class PjpyXmsbForm extends PjpyPjlcForm {

	private static final long serialVersionUID = 1L;

	PjpyXmszModel xmszModel;//项目设置Model

	private String nj;// 年级

	private String xydm;// 院系

	private String zydm;// 专业

	private String bjdm;// 班级

	private String xmdm;// 项目代码

	private String xmmc;// 项目名称

	private String szfw;// 设置范围

	private String bmdm;// 部门代码
	
	private String search_condition;//过滤条件

	private String[] pjxh;// 评奖学号

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getSzfw() {
		return szfw;
	}

	public void setSzfw(String szfw) {
		this.szfw = szfw;
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

	public String[] getPjxh() {
		return pjxh;
	}

	public void setPjxh(String[] pjxh) {
		this.pjxh = pjxh;
	}

	public PjpyXmszModel getXmszModel() {
		return xmszModel;
	}

	public void setXmszModel(PjpyXmszModel xmszModel) {
		this.xmszModel = xmszModel;
	}
	
	public String getSearch_condition() {
		return search_condition;
	}

	public void setSearch_condition(String searchCondition) {
		search_condition = searchCondition;
	}
}
