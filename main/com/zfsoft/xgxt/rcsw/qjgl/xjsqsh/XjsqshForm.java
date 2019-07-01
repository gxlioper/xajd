package com.zfsoft.xgxt.rcsw.qjgl.xjsqsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class XjsqshForm extends ActionForm {
	private String ywid;
	private String qjjgid;
	private String xh;
	private String xjr;
	private String xjbz;
	private String xjsj;
	private String xjfilepath;
	private String splc;
	private String shzt;
	private String sjkssj;
	private String sjjssj;
	private String type;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private String shyj;
	private String shjg;
	private String thgw;
	private String gwid;
	private String shid;
	private String shlc;
	private String sjqjts;
	//≈˙¡ø…Û∫À”√
	private String[] ids;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getSjqjts() {
		return sjqjts;
	}
	public void setSjqjts(String sjqjts) {
		this.sjqjts = sjqjts;
	}
	public String getShid() {
		return shid;
	}
	public void setShid(String shid) {
		this.shid = shid;
	}
	public String getShlc() {
		return shlc;
	}
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String[] getGwids() {
		return gwids;
	}
	public void setGwids(String[] gwids) {
		this.gwids = gwids;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	public String[] getSplcs() {
		return splcs;
	}
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}

	public String getYwid() {
		return ywid;
	}
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getShjg() {
		return shjg;
	}
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String getThgw() {
		return thgw;
	}
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	public String getGwid() {
		return gwid;
	}
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSjkssj() {
		return sjkssj;
	}
	public void setSjkssj(String sjkssj) {
		this.sjkssj = sjkssj;
	}
	public String getSjjssj() {
		return sjjssj;
	}
	public void setSjjssj(String sjjssj) {
		this.sjjssj = sjjssj;
	}
	public String getQjjgid() {
		return qjjgid;
	}
	public void setQjjgid(String qjjgid) {
		this.qjjgid = qjjgid;
	}
	public String getXjr() {
		return xjr;
	}
	public void setXjr(String xjr) {
		this.xjr = xjr;
	}
	public String getXjbz() {
		return xjbz;
	}
	public void setXjbz(String xjbz) {
		this.xjbz = xjbz;
	}
	public String getXjsj() {
		return xjsj;
	}
	public void setXjsj(String xjsj) {
		this.xjsj = xjsj;
	}
	public String getXjfilepath() {
		return xjfilepath;
	}
	public void setXjfilepath(String xjfilepath) {
		this.xjfilepath = xjfilepath;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
}
