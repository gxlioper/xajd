/**
 * @部门:学工产品事业部
 * @日期：2016-12-21 上午11:52:05 
 */  
package com.zfsoft.xgxt.xlzx.xlsc;

import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 心理筛查方法类
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2016-12-21 上午11:51:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlscjgForm extends ActionForm {
	private static final long serialVersionUID = 1966791940187986544L;
	private ExportModel exportModel = new ExportModel();//导出
	private Pages pages = new Pages();//分页
	private String type;//类型
	private SearchModel searchModel = new SearchModel();//查询
	
	private String id;//id
	private String xh;//学号
	private String scrq;//筛查日期（年月日）
	private String scl;//scl-90结果
	private String sds;//sds结果
	private String sas;//sas结果
	private String bkyy;//贝克抑郁症结果
	private String bkjl;//贝克焦虑症结果
	private String sfxyyt;//是否需要参加约谈
	private String sfyyt;//是否已参加约谈
	
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
	 * @return the scrq
	 */
	public String getScrq() {
		return scrq;
	}
	/**
	 * @param scrq要设置的 scrq
	 */
	public void setScrq(String scrq) {
		this.scrq = scrq;
	}
	/**
	 * @return the scl
	 */
	public String getScl() {
		return scl;
	}
	/**
	 * @param scl要设置的 scl
	 */
	public void setScl(String scl) {
		this.scl = scl;
	}
	/**
	 * @return the sds
	 */
	public String getSds() {
		return sds;
	}
	/**
	 * @param sds要设置的 sds
	 */
	public void setSds(String sds) {
		this.sds = sds;
	}
	/**
	 * @return the sas
	 */
	public String getSas() {
		return sas;
	}
	/**
	 * @param sas要设置的 sas
	 */
	public void setSas(String sas) {
		this.sas = sas;
	}
	/**
	 * @return the bkyy
	 */
	public String getBkyy() {
		return bkyy;
	}
	/**
	 * @param bkyy要设置的 bkyy
	 */
	public void setBkyy(String bkyy) {
		this.bkyy = bkyy;
	}
	/**
	 * @return the bkjl
	 */
	public String getBkjl() {
		return bkjl;
	}
	/**
	 * @param bkjl要设置的 bkjl
	 */
	public void setBkjl(String bkjl) {
		this.bkjl = bkjl;
	}
	/**
	 * @return the sfxyyt
	 */
	public String getSfxyyt() {
		return sfxyyt;
	}
	/**
	 * @param sfxyyt要设置的 sfxyyt
	 */
	public void setSfxyyt(String sfxyyt) {
		this.sfxyyt = sfxyyt;
	}
	/**
	 * @return the sfyyt
	 */
	public String getSfyyt() {
		return sfyyt;
	}
	/**
	 * @param sfyyt要设置的 sfyyt
	 */
	public void setSfyyt(String sfyyt) {
		this.sfyyt = sfyyt;
	}
}
