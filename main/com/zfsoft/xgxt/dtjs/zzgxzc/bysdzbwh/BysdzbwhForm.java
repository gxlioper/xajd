/**
 * @部门:学工产品事业部
 * @日期：2017年2月4日 下午2:32:15 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.bysdzbwh;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设-组织关系转出管理模块
 * @类功能描述: 毕业生党支部代码维护Form
 * @作者： xuwen[工号:1426]
 * @时间： 2017年2月4日 下午2:32:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BysdzbwhForm extends ActionForm{
	
	private static final long serialVersionUID = -1325439137478373664L;
	
	private String type;
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	
	private String dzbdm;	//党支部代码
	private String dzbmc;	//党支部名称
	
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
	 * @return the dzbdm
	 */
	public String getDzbdm() {
		return dzbdm;
	}
	/**
	 * @param dzbdm要设置的 dzbdm
	 */
	public void setDzbdm(String dzbdm) {
		this.dzbdm = dzbdm;
	}
	/**
	 * @return the dzbmc
	 */
	public String getDzbmc() {
		return dzbmc;
	}
	/**
	 * @param dzbmc要设置的 dzbmc
	 */
	public void setDzbmc(String dzbmc) {
		this.dzbmc = dzbmc;
	}
	
}
