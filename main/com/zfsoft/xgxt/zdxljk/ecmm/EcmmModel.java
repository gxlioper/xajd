/**
 * @部门:学工产品事业部
 * @日期：2015-2-11 上午09:40:06 
 */  
package com.zfsoft.xgxt.zdxljk.ecmm;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @类功能描述: 浙大心理健康--二次密码维护
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-2-11 上午09:11:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class EcmmModel extends ActionForm {

	
	private static final long serialVersionUID = -1035003519019910951L;

	private String zgh;
	private String ecmm;
	private String bz;
	private String cjsj;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	
	/**
	 * @return the cjsj
	 */
	public String getCjsj() {
		return cjsj;
	}
	/**
	 * @param cjsj要设置的 cjsj
	 */
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
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
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zgh要设置的 zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	/**
	 * @return the ecmm
	 */
	public String getEcmm() {
		return ecmm;
	}
	/**
	 * @param ecmm要设置的 ecmm
	 */
	public void setEcmm(String ecmm) {
		this.ecmm = ecmm;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	
	
}
