/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.rcsw.hczd;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @className	： HczdForm
 * @description	： 火车站点(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2017-11-22 下午03:49:27
 * @version 	V1.0 
 */

public class HczdForm extends ActionForm{
	
	
	/**
	 * @fields ：serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type; //操作类型
	private String zdmc; //名称
	private String zdpy; //字段拼音
	private String zdjp; //字段简拼
	private String shenfen; //省份 
	private String sfrmcs; //是否热门城市
	private String oriZdmc;
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @param 		：pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @param 		：searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @param 		：type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @return		: the zdmc
	 */
	public String getZdmc() {
		return zdmc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @param 		：zdmc the zdmc to set
	 */
	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @return		: the zdpy
	 */
	public String getZdpy() {
		return zdpy;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @param 		：zdpy the zdpy to set
	 */
	public void setZdpy(String zdpy) {
		this.zdpy = zdpy;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @return		: the zdjp
	 */
	public String getZdjp() {
		return zdjp;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @param 		：zdjp the zdjp to set
	 */
	public void setZdjp(String zdjp) {
		this.zdjp = zdjp;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @return		: the shenfen
	 */
	public String getShenfen() {
		return shenfen;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @param 		：shenfen the shenfen to set
	 */
	public void setShenfen(String shenfen) {
		this.shenfen = shenfen;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @return		: the sfrmcs
	 */
	public String getSfrmcs() {
		return sfrmcs;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-22 下午03:59:38 
	 * @param 		：sfrmcs the sfrmcs to set
	 */
	public void setSfrmcs(String sfrmcs) {
		this.sfrmcs = sfrmcs;
	}
	/**
	 * @description	： TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-23 下午01:49:18 
	 * @return		: the oriZdmc
	 */
	public String getOriZdmc() {
		return oriZdmc;
	}
	/**
	 * @description	：  TODO
	 * @author 		： 柳俊（1282）
	 * @date		： 2017-11-23 下午01:49:18 
	 * @param 		：oriZdmc the oriZdmc to set
	 */
	public void setOriZdmc(String oriZdmc) {
		this.oriZdmc = oriZdmc;
	}
	
	
}
