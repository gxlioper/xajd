package com.zfsoft.xgxt.jskp.cssz;

import org.apache.struts.action.ActionForm;

public class CsszForm extends ActionForm {
	private String id;
	private String splc;
	private String lx;
	private String sfsh;/*2017-11-19�޸Ĺ��ܡ��Ƿ���ˣ�0����1����*/
	private String[] ids;
	private String[] lxs;
	private String[] splcs;
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String[] getLxs() {
		return lxs;
	}
	public void setLxs(String[] lxs) {
		this.lxs = lxs;
	}
	public String[] getSplcs() {
		return splcs;
	}
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	/**
	 * @return the sfsh
	 */
	public String getSfsh() {
		return sfsh;
	}
	/**
	 * @param sfshҪ���õ� sfsh
	 */
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
}
