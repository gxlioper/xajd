/**
 * @部门:学工产品事业部
 * @日期：2013-10-25 上午09:07:01 
 */  
package com.zfsoft.xgxt.wjcf.cfyydmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分原因代码维护) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-25 上午09:07:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfyydmForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String cfyydm;            //处分原因代码
	private String cfyymc;            //处分原因名称
	private String kffs;              //扣分分数
	private String cjsj;

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
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
	 * @return the cfyydm
	 */
	public String getCfyydm() {
		return cfyydm;
	}
	/**
	 * @param cfyydm要设置的 cfyydm
	 */
	public void setCfyydm(String cfyydm) {
		this.cfyydm = cfyydm;
	}
	/**
	 * @return the cfyymc
	 */
	public String getCfyymc() {
		return cfyymc;
	}
	/**
	 * @param cfyymc要设置的 cfyymc
	 */
	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}
	public String getKffs() {
		return kffs;
	}
	public void setKffs(String kffs) {
		this.kffs = kffs;
	}
	
	
}
