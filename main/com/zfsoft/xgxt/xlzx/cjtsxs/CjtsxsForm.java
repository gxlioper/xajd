/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xlzx.cjtsxs;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @className	： CjtsxsForm
 * @description	： 春季特殊学生model(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2017-11-7 下午01:44:21
 * @version 	V1.0 
 */

public class CjtsxsForm extends ActionForm{
	
	/**
	 * @fields ：serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = -7110721289684115165L;
	private String id;
	private String xh;
	private String xn;
	private String xq;
	private String wtms;
	private String lrr;
	private String lrsj;
	private String xqmc;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @return		: the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @param 		：id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @param 		：xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @return		: the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @param 		：xn the xn to set
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @return		: the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @param 		：xq the xq to set
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @return		: the wtms
	 */
	public String getWtms() {
		return wtms;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @param 		：wtms the wtms to set
	 */
	public void setWtms(String wtms) {
		this.wtms = wtms;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @return		: the lrr
	 */
	public String getLrr() {
		return lrr;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @param 		：lrr the lrr to set
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @return		: the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @param 		：lrsj the lrsj to set
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @param 		：exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午01:48:58 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午03:04:37 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午03:04:37 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午05:33:06 
	 * @return		: the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-7 下午05:33:06 
	 * @param 		：xqmc the xqmc to set
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	
	
	
	
	
}
