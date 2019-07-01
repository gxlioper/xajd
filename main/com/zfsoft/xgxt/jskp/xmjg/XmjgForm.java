package com.zfsoft.xgxt.jskp.xmjg;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class XmjgForm extends ActionForm {
	private String xmid;
    private String xmmc;
    private String zdbm;
    private String xmlb;
    private String lxsj;
    private String fzr;
    private String fzrlxfs;
    private String zdls;
    private String zdlslxfs;
    private String filepath;
    private String lxly;
    private String splcid;
    private String shzt;
    private String zdf;
    private String zxf;
    private String saveFlag;
    private String xh;
    private String xm;
    private String xmdl;
    private String sjly;
    private String xmsqid;
    private String kgzt;
    private String sbkssj;
    private String sbjssj;
    private String jgsbzq;
    private String[] xhs;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	public String getKgzt() {
		return kgzt;
	}
	public void setKgzt(String kgzt) {
		this.kgzt = kgzt;
	}
	public String getSbkssj() {
		return sbkssj;
	}
	public void setSbkssj(String sbkssj) {
		this.sbkssj = sbkssj;
	}
	public String getSbjssj() {
		return sbjssj;
	}
	public void setSbjssj(String sbjssj) {
		this.sbjssj = sbjssj;
	}
	public String getJgsbzq() {
		return jgsbzq;
	}
	public void setJgsbzq(String jgsbzq) {
		this.jgsbzq = jgsbzq;
	}
    public String getXmsqid() {
		return xmsqid;
	}
	public void setXmsqid(String xmsqid) {
		this.xmsqid = xmsqid;
	}
    public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	public String getXmdl() {
		return xmdl;
	}
	public void setXmdl(String xmdl) {
		this.xmdl = xmdl;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getZdbm() {
		return zdbm;
	}
	public String getXmid() {
		return xmid;
	}
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	public void setZdbm(String zdbm) {
		this.zdbm = zdbm;
	}
	public String getXmlb() {
		return xmlb;
	}
	public void setXmlb(String xmlb) {
		this.xmlb = xmlb;
	}
	public String getLxsj() {
		return lxsj;
	}
	public void setLxsj(String lxsj) {
		this.lxsj = lxsj;
	}
	public String getFzr() {
		return fzr;
	}
	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	public String getFzrlxfs() {
		return fzrlxfs;
	}
	public void setFzrlxfs(String fzrlxfs) {
		this.fzrlxfs = fzrlxfs;
	}
	public String getZdls() {
		return zdls;
	}
	public void setZdls(String zdls) {
		this.zdls = zdls;
	}
	public String getZdlslxfs() {
		return zdlslxfs;
	}
	public void setZdlslxfs(String zdlslxfs) {
		this.zdlslxfs = zdlslxfs;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getLxly() {
		return lxly;
	}
	public void setLxly(String lxly) {
		this.lxly = lxly;
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
	public String getZdf() {
		return zdf;
	}
	public void setZdf(String zdf) {
		this.zdf = zdf;
	}
	public String getZxf() {
		return zxf;
	}
	public void setZxf(String zxf) {
		this.zxf = zxf;
	}
	public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
}
