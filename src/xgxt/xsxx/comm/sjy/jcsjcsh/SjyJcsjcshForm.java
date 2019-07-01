package xgxt.xsxx.comm.sjy.jcsjcsh;

import xgxt.xsxx.comm.XsxxCommForm;

public class SjyJcsjcshForm extends XsxxCommForm {

	private static final long serialVersionUID = 1L;

	private String czxm;// 操作部门

	private String bmjb;// 部门级别

	private String bmlb;// 部门类别

	private String bmdm;// 部门代码

	private String bmmc;// 部门名称

	private String bmfdm;// 部门父代码

	private String fbmmc;// 父部门名称

	private String rule;// 规则

	private String zd;// 字段

	private String lyb;// 来源表

	private String[] zdq;// 制定前

	private String[] zdh;// 制定后

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getFbmmc() {
		return fbmmc;
	}

	public void setFbmmc(String fbmmc) {
		this.fbmmc = fbmmc;
	}

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getBmfdm() {
		return bmfdm;
	}

	public void setBmfdm(String bmfdm) {
		this.bmfdm = bmfdm;
	}

	public String getBmjb() {
		return bmjb;
	}

	public void setBmjb(String bmjb) {
		this.bmjb = bmjb;
	}

	public String getBmlb() {
		return bmlb;
	}

	public void setBmlb(String bmlb) {
		this.bmlb = bmlb;
	}

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getCzxm() {
		return czxm;
	}

	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}

	public String getLyb() {
		return lyb;
	}

	public void setLyb(String lyb) {
		this.lyb = lyb;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String[] getZdh() {
		return zdh;
	}

	public void setZdh(String[] zdh) {
		this.zdh = zdh;
	}

	public String[] getZdq() {
		return zdq;
	}

	public void setZdq(String[] zdq) {
		this.zdq = zdq;
	}
}
