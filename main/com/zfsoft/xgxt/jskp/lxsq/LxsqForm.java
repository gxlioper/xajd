package com.zfsoft.xgxt.jskp.lxsq;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class LxsqForm extends ActionForm {
	private String sqid;
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
    private String sjly;/*是否是通过立项申请、立项审核过来的数据。YES-是、NO-否*/
    private String tjsf;/*用于参数设置为0时的修改提交判断，【修改提交】用到*/
    private String lxxn;/*立项学年*/
    private String sjlrr;/*数据录入人*/
    private FormFile drmb;/*导入模板*/
    private String exclePath;/*导入模板路径*/
    
    
    
    
	/**
	 * @return the sjlrr
	 */
	public String getSjlrr() {
		return sjlrr;
	}
	/**
	 * @param sjlrr要设置的 sjlrr
	 */
	public void setSjlrr(String sjlrr) {
		this.sjlrr = sjlrr;
	}
	/**
	 * @return the exclePath
	 */
	public String getExclePath() {
		return exclePath;
	}
	/**
	 * @param exclePath要设置的 exclePath
	 */
	public void setExclePath(String exclePath) {
		this.exclePath = exclePath;
	}
	/**
	 * @return the drmb
	 */
	public FormFile getDrmb() {
		return drmb;
	}
	/**
	 * @param drmb要设置的 drmb
	 */
	public void setDrmb(FormFile drmb) {
		this.drmb = drmb;
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
	private String[] xhs;
	private SearchModel searchModel = new SearchModel();
    public String getSaveFlag() {
		return saveFlag;
	}
	public void setSaveFlag(String saveFlag) {
		this.saveFlag = saveFlag;
	}
    public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
    public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	private Pages pages = new Pages();
	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjly要设置的 sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
	}
	/**
	 * @return the tjsf
	 */
	public String getTjsf() {
		return tjsf;
	}
	/**
	 * @param tjsf要设置的 tjsf
	 */
	public void setTjsf(String tjsf) {
		this.tjsf = tjsf;
	}
	/**
	 * @return the lxxn
	 */
	public String getLxxn() {
		return lxxn;
	}
	/**
	 * @param lxxn要设置的 lxxn
	 */
	public void setLxxn(String lxxn) {
		this.lxxn = lxxn;
	}
}
