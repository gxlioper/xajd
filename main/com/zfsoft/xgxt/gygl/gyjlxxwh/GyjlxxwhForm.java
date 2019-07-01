package com.zfsoft.xgxt.gygl.gyjlxxwh;


import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

public class GyjlxxwhForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	private String xh;
	private String wjid;
	private String wjxn;  //违纪学年
	private String wjxq;  //违纪学期
	private String jldldm;//纪律大类代码
	private String jldlmc;//纪律大类名称
	private String jllbdm;//纪律类别代码
	private String jllbmc;//纪律类别名称
	private String gyjllbdldm;
	private String gyjllbdm;
	private String dcqk;//调查情况
	private String wjsj;//违纪时间
	private String czr;//操作人
	private String bz;//备注
	private String ylzd1;
	private String ylzd2;
	private String ylzd3;
	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xh要设置的 xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the wjid
	 */
	public String getWjid() {
		return wjid;
	}
	/**
	 * @param wjid要设置的 wjid
	 */
	public void setWjid(String wjid) {
		this.wjid = wjid;
	}
	
	/**
	 * @return the wjxn
	 */
	public String getWjxn() {
		return wjxn;
	}
	/**
	 * @param wjxn要设置的 wjxn
	 */
	public void setWjxn(String wjxn) {
		this.wjxn = wjxn;
	}
	/**
	 * @return the wjxq
	 */
	public String getWjxq() {
		return wjxq;
	}
	/**
	 * @param wjxq要设置的 wjxq
	 */
	public void setWjxq(String wjxq) {
		this.wjxq = wjxq;
	}
	/**
	 * @return the jldldm
	 */
	public String getJldldm() {
		return jldldm;
	}
	/**
	 * @param jldldm要设置的 jldldm
	 */
	public void setJldldm(String jldldm) {
		this.jldldm = jldldm;
	}
	/**
	 * @return the jldlmc
	 */
	public String getJldlmc() {
		return jldlmc;
	}
	/**
	 * @param jldlmc要设置的 jldlmc
	 */
	public void setJldlmc(String jldlmc) {
		this.jldlmc = jldlmc;
	}
	/**
	 * @return the jllbdm
	 */
	public String getJllbdm() {
		return jllbdm;
	}
	/**
	 * @param jllbdm要设置的 jllbdm
	 */
	public void setJllbdm(String jllbdm) {
		this.jllbdm = jllbdm;
	}
	/**
	 * @return the jllbmc
	 */
	public String getJllbmc() {
		return jllbmc;
	}
	/**
	 * @param jllbmc要设置的 jllbmc
	 */
	public void setJllbmc(String jllbmc) {
		this.jllbmc = jllbmc;
	}
	/**
	 * @return the dcqk
	 */
	public String getDcqk() {
		return dcqk;
	}
	/**
	 * @param dcqk要设置的 dcqk
	 */
	public void setDcqk(String dcqk) {
		this.dcqk = dcqk;
	}
	/**
	 * @return the wjsj
	 */
	public String getWjsj() {
		return wjsj;
	}
	/**
	 * @param wjsj要设置的 wjsj
	 */
	public void setWjsj(String wjsj) {
		this.wjsj = wjsj;
	}
	/**
	 * @return the czr
	 */
	public String getCzr() {
		return czr;
	}
	/**
	 * @param czr要设置的 czr
	 */
	public void setCzr(String czr) {
		this.czr = czr;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the ylzd1
	 */
	public String getYlzd1() {
		return ylzd1;
	}
	/**
	 * @param ylzd1要设置的 ylzd1
	 */
	public void setYlzd1(String ylzd1) {
		this.ylzd1 = ylzd1;
	}
	/**
	 * @return the ylzd2
	 */
	public String getYlzd2() {
		return ylzd2;
	}
	/**
	 * @param ylzd2要设置的 ylzd2
	 */
	public void setYlzd2(String ylzd2) {
		this.ylzd2 = ylzd2;
	}
	/**
	 * @return the ylzd3
	 */
	public String getYlzd3() {
		return ylzd3;
	}
	/**
	 * @param ylzd3要设置的 ylzd3
	 */
	public void setYlzd3(String ylzd3) {
		this.ylzd3 = ylzd3;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModel要设置的 searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pages要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return the gyjllbdldm
	 */
	public String getGyjllbdldm() {
		return gyjllbdldm;
	}
	/**
	 * @param gyjllbdldm要设置的 gyjllbdldm
	 */
	public void setGyjllbdldm(String gyjllbdldm) {
		this.gyjllbdldm = gyjllbdldm;
	}
	/**
	 * @return the gyjllbdm
	 */
	public String getGyjllbdm() {
		return gyjllbdm;
	}
	/**
	 * @param gyjllbdm要设置的 gyjllbdm
	 */
	public void setGyjllbdm(String gyjllbdm) {
		this.gyjllbdm = gyjllbdm;
	}
	
	
	
}
