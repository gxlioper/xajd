package com.zfsoft.xgxt.pjpy.hjsq.sh;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class HjshForm extends ActionForm {
	private String id;
	private String xh;
	private String xn;
	private String xq;
	private String hjmc;
	private String hjsj;
	private String fjdw;
	private String fj;
	private String shzt;
	private String splc;
	private SearchModel searchModel = new SearchModel();
    private String type;
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	
	//ÉóºË×Ö¶Î
	private String shid;
	private String thgw;
	private String gwid;
	private String shlc;
	private String shjg;
	private String shyj;
	private String[] ids;
	private String[] gwids;
	private String[] xhs;
	private String[] splcs;
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
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
	public String getHjmc() {
		return hjmc;
	}
	public void setHjmc(String hjmc) {
		this.hjmc = hjmc;
	}
	public String getHjsj() {
		return hjsj;
	}
	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}
	public String getFjdw() {
		return fjdw;
	}
	public void setFjdw(String fjdw) {
		this.fjdw = fjdw;
	}
	public String getFj() {
		return fj;
	}
	public void setFj(String fj) {
		this.fj = fj;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
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
	
	
}
