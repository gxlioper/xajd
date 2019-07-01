package xsgzgl.pjpy.general.index;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_首页_通用_Model
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyIndexModel {

	private String pjxn;// 评奖学年

	private String pjxq;// 评奖学期

	private String pjzq;// 评奖周期

	private String zcpm;// 综测排名

	private String zypm;// 智育排名

	private String cpz;// 参评组

	private String start;// 开始新评奖

	private String[] operate;// 操作

	private String maxPjlc;// 最大评奖流程

	private String[] lcdm;// 流程代码

	private String[] lcdj;// 流程等级

	private String stylePath;// 样式地址

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
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

	public String getStylePath() {
		return stylePath;
	}

	public void setStylePath(String stylePath) {
		this.stylePath = stylePath;
	}

	public void setPjxq(String pjxq) {
		this.pjxq = pjxq;
	}

	public String getPjzq() {
		return pjzq;
	}

	public void setPjzq(String pjzq) {
		this.pjzq = pjzq;
	}

	public String getZcpm() {
		return zcpm;
	}

	public void setZcpm(String zcpm) {
		this.zcpm = zcpm;
	}

	public String getCpz() {
		return cpz;
	}

	public void setCpz(String cpz) {
		this.cpz = cpz;
	}

	public String[] getOperate() {
		return operate;
	}

	public void setOperate(String[] operate) {
		this.operate = operate;
	}

	public String getMaxPjlc() {
		return maxPjlc;
	}

	public void setMaxPjlc(String maxPjlc) {
		this.maxPjlc = maxPjlc;
	}

	public String[] getLcdm() {
		return lcdm;
	}

	public void setLcdm(String[] lcdm) {
		this.lcdm = lcdm;
	}

	public String[] getLcdj() {
		return lcdj;
	}

	public void setLcdj(String[] lcdj) {
		this.lcdj = lcdj;
	}

	public String getZypm() {
		return zypm;
	}

	public void setZypm(String zypm) {
		this.zypm = zypm;
	}
}
