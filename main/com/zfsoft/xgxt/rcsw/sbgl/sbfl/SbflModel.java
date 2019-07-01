/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:41:01 
 */  
package com.zfsoft.xgxt.rcsw.sbgl.sbfl;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @类功能描述: 设备分类维护
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:41:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SbflModel extends ActionForm {

	
	private static final long serialVersionUID = 7426339785751241000L;
	
	private String dm;
	private String mc;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	
	/**
	 * @return the dm
	 */
	public String getDm() {
		return dm;
	}
	/**
	 * @param dm要设置的 dm
	 */
	public void setDm(String dm) {
		this.dm = dm;
	}
	/**
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}
	/**
	 * @param mc要设置的 mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
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
	
	

}
