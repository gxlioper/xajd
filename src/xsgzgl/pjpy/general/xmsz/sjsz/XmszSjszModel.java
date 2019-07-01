package xsgzgl.pjpy.general.xmsz.sjsz;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_时间设置_Model
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

public class XmszSjszModel {

	private String xmdm;// 项目代码

	private String pjxn;// 学年

	private String pjxq;// 学期

	private String pjnd;// 年度

	private String sqkssj;// 申请开始时间

	private String sqjssj;// 申请结束时间

	private String shkssj;// 审核开始时间

	private String shjssj;// 审核结束时间
	
	private String sqkzkg;// 申请控制开关

	private String shkzkg;// 审核结束时间
	
	private String bz;// 备注
	
	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getPjxn() {
		return pjxn;
	}

	public void setPjxn(String pjxn) {
		this.pjxn = pjxn;
	}

	public String getPjxq() {
		return pjxq;
	}

	public void setPjxq(String pjxq) {
		this.pjxq = pjxq;
	}

	public String getPjnd() {
		return pjnd;
	}

	public void setPjnd(String pjnd) {
		this.pjnd = pjnd;
	}

	public String getSqkssj() {
		return sqkssj;
	}

	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}

	public String getSqjssj() {
		return sqjssj;
	}

	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}

	public String getShkssj() {
		return shkssj;
	}

	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}

	public String getShjssj() {
		return shjssj;
	}

	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}

	public String getSqkzkg() {
		return sqkzkg;
	}

	public void setSqkzkg(String sqkzkg) {
		this.sqkzkg = sqkzkg;
	}

	public String getShkzkg() {
		return shkzkg;
	}

	public void setShkzkg(String shkzkg) {
		this.shkzkg = shkzkg;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
}
