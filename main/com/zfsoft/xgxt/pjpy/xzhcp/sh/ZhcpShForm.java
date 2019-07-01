package com.zfsoft.xgxt.pjpy.xzhcp.sh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class ZhcpShForm extends ActionForm {
	private String sqid;
	private String xn;
	private String xq;
	private String xh;
	private String fjpath;
	private String grpc;
	private String shzt;
	private String splc;
	private String type;
	private String shjg;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	
	//ÉóºË×Ö¶Î
	private String shid;
	private String thgw;
	private String gwid;
	private String shlc;
	
	private String[] sqids;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
	public String getShlc() {
		return shlc;
	}
	public void setShlc(String shlc) {
		this.shlc = shlc;
	}
	public String[] getSplcs() {
		return splcs;
	}
	public void setSplcs(String[] splcs) {
		this.splcs = splcs;
	}
	private String shyj;
	
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
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getShid() {
		return shid;
	}
	public void setShid(String shid) {
		this.shid = shid;
	}
	public String[] getSqids() {
		return sqids;
	}
	public void setSqids(String[] sqids) {
		this.sqids = sqids;
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
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getFjpath() {
		return fjpath;
	}
	public void setFjpath(String fjpath) {
		this.fjpath = fjpath;
	}
	public String getGrpc() {
		return grpc;
	}
	public void setGrpc(String grpc) {
		this.grpc = grpc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
}
