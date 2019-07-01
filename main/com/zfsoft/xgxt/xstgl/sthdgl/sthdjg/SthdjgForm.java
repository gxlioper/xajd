package com.zfsoft.xgxt.xstgl.sthdgl.sthdjg;

import org.apache.struts.action.ActionForm;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class SthdjgForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;

	private String hdid;
	private String xh;
	private String hdmc;//活动名称
	private String fwsj;//服务时间
	private String fwsc;//服务时长
	private String fwdd;//服务地点
	private String zbdw;//主办单位
	private String fjid;
	private String splc;
	private String shzt;
	private String lrr;
	private String lrsj;
	private String id;
	private String sjly;
	private String fwddssx;//服务地点省市县

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHdid() {
		return hdid;
	}

	public void setHdid(String hdid) {
		this.hdid = hdid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getHdmc() {
		return hdmc;
	}

	public void setHdmc(String hdmc) {
		this.hdmc = hdmc;
	}

	public String getFwsj() {
		return fwsj;
	}

	public void setFwsj(String fwsj) {
		this.fwsj = fwsj;
	}

	public String getFwsc() {
		return fwsc;
	}

	public void setFwsc(String fwsc) {
		this.fwsc = fwsc;
	}

	public String getFwdd() {
		return fwdd;
	}

	public void setFwdd(String fwdd) {
		this.fwdd = fwdd;
	}

	public String getZbdw() {
		return zbdw;
	}

	public void setZbdw(String zbdw) {
		this.zbdw = zbdw;
	}

	public String getFjid() {
		return fjid;
	}

	public void setFjid(String fjid) {
		this.fjid = fjid;
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

	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSjly() {
		return sjly;
	}

	public void setSjly(String sjly) {
		this.sjly = sjly;
	}

	public String getFwddssx() {
		return fwddssx;
	}

	public void setFwddssx(String fwddssx) {
		this.fwddssx = fwddssx;
	}
}
