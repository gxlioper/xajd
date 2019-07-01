/**
 * @部门:学工产品事业部
 * @日期：2016-2-26 下午04:56:11 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.pfz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 传媒卫生分_评分组
 * @作者： cq [工号:785]
 * @时间： 2016-2-26 下午04:56:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PfzForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String pfzid;	//评分组ID
	private String pfzmc;	//评分组名称
	private String ssxq;	//所属校区
	private String zgh;
	private String xm;
	private String bmdm;
	private String xbdm;
	private String fpzt;	//分配状态
	
	
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
	 * @return the pfzid
	 */
	public String getPfzid() {
		return pfzid;
	}
	/**
	 * @param pfzid要设置的 pfzid
	 */
	public void setPfzid(String pfzid) {
		this.pfzid = pfzid;
	}
	/**
	 * @return the pfzmc
	 */
	public String getPfzmc() {
		return pfzmc;
	}
	/**
	 * @param pfzmc要设置的 pfzmc
	 */
	public void setPfzmc(String pfzmc) {
		this.pfzmc = pfzmc;
	}
	/**
	 * @return the ssxq
	 */
	public String getSsxq() {
		return ssxq;
	}
	/**
	 * @param ssxq要设置的 ssxq
	 */
	public void setSsxq(String ssxq) {
		this.ssxq = ssxq;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the xbdm
	 */
	public String getXbdm() {
		return xbdm;
	}
	/**
	 * @param xbdm要设置的 xbdm
	 */
	public void setXbdm(String xbdm) {
		this.xbdm = xbdm;
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
	 * @return the bmdm
	 */
	public String getBmdm() {
		return bmdm;
	}
	/**
	 * @param bmdm要设置的 bmdm
	 */
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	/**
	 * @return the fpzt
	 */
	public String getFpzt() {
		return fpzt;
	}
	/**
	 * @param fpzt要设置的 fpzt
	 */
	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}
	

}
