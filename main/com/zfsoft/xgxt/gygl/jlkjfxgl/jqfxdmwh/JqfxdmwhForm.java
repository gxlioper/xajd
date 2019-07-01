/**
 * @部门:学工产品事业部
 * @日期：2016-2-19 上午10:01:13 
 */  
package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxdmwh;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理假期返校代码维护管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： Dulq[工号:995]
 * @时间： 2016-2-19 上午10:01:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
@SuppressWarnings("serial")
public class JqfxdmwhForm extends ActionForm{
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	private String fxdm;//返校代码
	private String fxmc;//返校名称
	
	
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
	 * @return the fxdm
	 */
	public String getFxdm() {
		return fxdm;
	}
	/**
	 * @param fxdm要设置的 fxdm
	 */
	public void setFxdm(String fxdm) {
		this.fxdm = fxdm;
	}
	
	/**
	 * @return the fxmc
	 */
	public String getFxmc() {
		return fxmc;
	}
	/**
	 * @param fxmc要设置的 fxmc
	 */
	public void setFxmc(String fxmc) {
		this.fxmc = fxmc;
	}
	
    
	
}
