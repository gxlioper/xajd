/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.szdw.sygl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

public class SyglForm extends ActionForm{
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type; 
	private String sydm; //书院代码
	private String symc; //书院名称
	
	private String fpzt; //分配类型   待分配/已分配
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-23 下午05:44:17 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-23 下午05:44:17 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-23 下午05:44:17 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-23 下午05:44:17 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-23 下午05:44:17 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-23 下午05:44:17 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-23 下午05:44:17 
	 * @return		: the sydm
	 */
	public String getSydm() {
		return sydm;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-23 下午05:44:17 
	 * @param 		：sydm the sydm to set
	 */
	public void setSydm(String sydm) {
		this.sydm = sydm;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-23 下午05:44:17 
	 * @return		: the symc
	 */
	public String getSymc() {
		return symc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-23 下午05:44:17 
	 * @param 		：symc the symc to set
	 */
	public void setSymc(String symc) {
		this.symc = symc;
	}
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-24 下午03:33:08 
	 * @return		: the fpzt
	 */
	public String getFpzt() {
		return fpzt;
	}
	/**
	 * @description	：  TODO
	 * @author 		： CP（1352）
	 * @date		： 2018-1-24 下午03:33:08 
	 * @param 		：fpzt the fpzt to set
	 */
	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}
	
	
}
