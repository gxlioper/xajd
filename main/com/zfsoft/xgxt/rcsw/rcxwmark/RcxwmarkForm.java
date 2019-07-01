/**
 * @部门:学工产品事业部
 * @日期：2016-10-28 下午02:43:20 
 */  
package com.zfsoft.xgxt.rcsw.rcxwmark;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常行为分标记管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-10-28 下午02:43:20 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RcxwmarkForm extends ActionForm {
	private String id;//业务表id，主键
	private String rcxwjgid; //日常行为结果id
	private String jxdm;//奖项代码
	private String pjxn;//评奖学年
	private String bz;//备注
	private String czr;//操作人
	private String czsj;//操作时间
	private String type;//标志位
	//导出
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private static final long serialVersionUID = 1L;
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
	 * @return the rcxwjgid
	 */
	public String getRcxwjgid() {
		return rcxwjgid;
	}
	/**
	 * @param rcxwjgid要设置的 rcxwjgid
	 */
	public void setRcxwjgid(String rcxwjgid) {
		this.rcxwjgid = rcxwjgid;
	}
	/**
	 * @return the jxdm
	 */
	public String getJxdm() {
		return jxdm;
	}
	/**
	 * @param jxdm要设置的 jxdm
	 */
	public void setJxdm(String jxdm) {
		this.jxdm = jxdm;
	}
	/**
	 * @return the pjxn
	 */
	public String getPjxn() {
		return pjxn;
	}
	/**
	 * @param pjxn要设置的 pjxn
	 */
	public void setPjxn(String pjxn) {
		this.pjxn = pjxn;
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
	/**
	 * @return the czr
	 */
	public String getCzr() {
		return czr;
	}
	/**
	 * @param czr要设置的 czr
	 */
	public void setCzr(String czr) {
		this.czr = czr;
	}
	/**
	 * @return the czsj
	 */
	public String getCzsj() {
		return czsj;
	}
	/**
	 * @param czsj要设置的 czsj
	 */
	public void setCzsj(String czsj) {
		this.czsj = czsj;
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
	
	

	
}
