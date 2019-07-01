/**
 * @部门:学工产品事业部
 * @日期：2016-1-20 下午03:15:35 
 */  
package com.zfsoft.xgxt.rcsw.sjgl.kqsj;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-1-20 下午03:15:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqsjForm extends ActionForm{
	//导出模型
	ExportModel exportModel = new ExportModel();
	//高级搜搜
	SearchModel searchModel = new SearchModel();
	// 分页
	Pages pages = new Pages();
	
	private String id;
	private String xh;
	private String xn;
	private String dyxqsjts;    //第一学期事假天数
	private String dexqsjts;    //第二学期事假天数
	private String dyxqbjts;    //第一学期病假天数
	private String dexqbjts;	//第二学期病假天数		
	private String kkxs;		//旷课学时
	private String cdztcs;		//迟到早退次数
	private String type;
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
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
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the dyxqsjts
	 */
	public String getDyxqsjts() {
		return dyxqsjts;
	}
	/**
	 * @param dyxqsjts要设置的 dyxqsjts
	 */
	public void setDyxqsjts(String dyxqsjts) {
		this.dyxqsjts = dyxqsjts;
	}
	/**
	 * @return the dexqsjts
	 */
	public String getDexqsjts() {
		return dexqsjts;
	}
	/**
	 * @param dexqsjts要设置的 dexqsjts
	 */
	public void setDexqsjts(String dexqsjts) {
		this.dexqsjts = dexqsjts;
	}
	/**
	 * @return the dyxqbjts
	 */
	public String getDyxqbjts() {
		return dyxqbjts;
	}
	/**
	 * @param dyxqbjts要设置的 dyxqbjts
	 */
	public void setDyxqbjts(String dyxqbjts) {
		this.dyxqbjts = dyxqbjts;
	}
	/**
	 * @return the dexqbjts
	 */
	public String getDexqbjts() {
		return dexqbjts;
	}
	/**
	 * @param dexqbjts要设置的 dexqbjts
	 */
	public void setDexqbjts(String dexqbjts) {
		this.dexqbjts = dexqbjts;
	}
	/**
	 * @return the kkxs
	 */
	public String getKkxs() {
		return kkxs;
	}
	/**
	 * @param kkxs要设置的 kkxs
	 */
	public void setKkxs(String kkxs) {
		this.kkxs = kkxs;
	}
	/**
	 * @return the cdztcs
	 */
	public String getCdztcs() {
		return cdztcs;
	}
	/**
	 * @param cdztcs要设置的 cdztcs
	 */
	public void setCdztcs(String cdztcs) {
		this.cdztcs = cdztcs;
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
	
	
	
	
}
