/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午11:26:25 
 */  
package com.zfsoft.xgxt.ybgzz.cywh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @类功能描述: 易班成员维护 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-1-30 下午01:56:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YbcyModel extends ActionForm {

	
	private static final long serialVersionUID = 1966791940187986544L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String id;
	private String xh;
	private String sjly;
	private String sqjrsj;
	private String sqly;
	private String sqsj;
	
	private String tcsj;
	private String tcyy;
	
	
	
	/**
	 * @return the tcsj
	 */
	public String getTcsj() {
		return tcsj;
	}
	/**
	 * @param tcsj要设置的 tcsj
	 */
	public void setTcsj(String tcsj) {
		this.tcsj = tcsj;
	}
	/**
	 * @return the tcyy
	 */
	public String getTcyy() {
		return tcyy;
	}
	/**
	 * @param tcyy要设置的 tcyy
	 */
	public void setTcyy(String tcyy) {
		this.tcyy = tcyy;
	}
	/**
	 * @return the sqjrsj
	 */
	public String getSqjrsj() {
		return sqjrsj;
	}
	/**
	 * @param sqjrsj要设置的 sqjrsj
	 */
	public void setSqjrsj(String sqjrsj) {
		this.sqjrsj = sqjrsj;
	}
	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqly要设置的 sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
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
	
	
}
