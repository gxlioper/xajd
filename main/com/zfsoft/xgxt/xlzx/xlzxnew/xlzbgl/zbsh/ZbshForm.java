package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class ZbshForm extends ActionForm {
	private String sbsqid;
	private String xh;
	private String sbsj;
	private String sbzbid;
	private String ztqk;
	private String xlxsxxqk;
	private String bz;
	private String splcid;
	private String shzt;
	private String xn;
	private String xm;
	private String bjdm;
	private String xq;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	//审核用
	 private String shid;
	 private String shjg;
	 private String shyj;
	 private String gwid;
	 private String shlc;
	private String thgw;
	 private String filepath;
	 //批量审核用
	 private String[] id;
	 private String[] gwids;
	 private String[] xhs;
	 private String[] splcids;
	 public String getShlc() {
			return shlc;
	}
		public void setShlc(String shlc) {
			this.shlc = shlc;
	}
	public String[] getSplcids() {
		return splcids;
	}
	public void setSplcids(String[] splcids) {
		this.splcids = splcids;
	}
	public String getSbsqid() {
		return sbsqid;
	}
	public void setSbsqid(String sbsqid) {
		this.sbsqid = sbsqid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getSbsj() {
		return sbsj;
	}
	public void setSbsj(String sbsj) {
		this.sbsj = sbsj;
	}
	public String getSbzbid() {
		return sbzbid;
	}
	public void setSbzbid(String sbzbid) {
		this.sbzbid = sbzbid;
	}
	public String getZtqk() {
		return ztqk;
	}
	public void setZtqk(String ztqk) {
		this.ztqk = ztqk;
	}
	public String getXlxsxxqk() {
		return xlxsxxqk;
	}
	public void setXlxsxxqk(String xlxsxxqk) {
		this.xlxsxxqk = xlxsxxqk;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getSplcid() {
		return splcid;
	}
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getShid() {
		return shid;
	}
	public void setShid(String shid) {
		this.shid = shid;
	}
	public String getShjg() {
		return shjg;
	}
	public void setShjg(String shjg) {
		this.shjg = shjg;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getGwid() {
		return gwid;
	}
	public void setGwid(String gwid) {
		this.gwid = gwid;
	}
	public String getThgw() {
		return thgw;
	}
	public void setThgw(String thgw) {
		this.thgw = thgw;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
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
	
}
