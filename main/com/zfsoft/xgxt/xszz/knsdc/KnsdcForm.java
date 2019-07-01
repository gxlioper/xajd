package com.zfsoft.xgxt.xszz.knsdc;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class KnsdcForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String dcdm ;//档次代码
	private String dcmc ;//档次名称
	private String xmsm ;//项目说明
	private String yydm; //困难原因
	private String yymc;// 困难原因名称
	
	private String xh;//困难原因序号
	
	private String zme;//各档次总名额
	
	/**
	 * @return the zme
	 */
	public String getZme() {
		return zme;
	}
	/**
	 * @param zme要设置的 zme
	 */
	public void setZme(String zme) {
		this.zme = zme;
	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
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
	public String getDcdm() {
		return dcdm;
	}
	public void setDcdm(String dcdm) {
		this.dcdm = dcdm;
	}
	public String getDcmc() {
		return dcmc;
	}
	public void setDcmc(String dcmc) {
		this.dcmc = dcmc;
	}
	public String getXmsm() {
		return xmsm;
	}
	public void setXmsm(String xmsm) {
		this.xmsm = xmsm;
	}
	/**
	 * @return the yydm
	 */
	public String getYydm() {
		return yydm;
	}
	/**
	 * @param yydm要设置的 yydm
	 */
	public void setYydm(String yydm) {
		this.yydm = yydm;
	}
	/**
	 * @return the yymc
	 */
	public String getYymc() {
		return yymc;
	}
	/**
	 * @param yymc要设置的 yymc
	 */
	public void setYymc(String yymc) {
		this.yymc = yymc;
	}
	
	


}
