/**
 * @部门:学工产品（1）部
 * @日期：2017-6-22 上午09:41:52 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.zcwh;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 综测分维护
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-6-22 上午09:41:52 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcwhForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel();/*高级查询*/
	private ExportModel exportModel = new ExportModel();/*导出*/
	private Pages pages = new Pages();/*分页*/
	private String doType;/*类型*/
	private String editType;/*判断综测项目是编辑模式还是查看模式*/
	
	private String id;/*ID*/
	private String xh;/*学号*/
	private String xn;/*学年*/
	private String fs;/*分数*/
	private String lrr;/*录入人*/
	private String lrsj;/*录入时间*/
	private String xmdm;/*项目代码*/
	private FormFile importFile;/*导入*/
	private String qxyy;/*取消提交综测分原因*/
	private String xmdms;
	private String zcxmdmForTop;
	
	
	
	/**
	 * @return the zcxmdmForTop
	 */
	public String getZcxmdmForTop() {
		return zcxmdmForTop;
	}
	/**
	 * @param zcxmdmForTop要设置的 zcxmdmForTop
	 */
	public void setZcxmdmForTop(String zcxmdmForTop) {
		this.zcxmdmForTop = zcxmdmForTop;
	}
	/**
	 * @return the qxyy
	 */
	public String getQxyy() {
		return qxyy;
	}
	/**
	 * @param qxyy要设置的 qxyy
	 */
	public void setQxyy(String qxyy) {
		this.qxyy = qxyy;
	}
	/**
	 * @return the importFile
	 */
	public FormFile getImportFile() {
		return importFile;
	}
	/**
	 * @param importFile要设置的 importFile
	 */
	public void setImportFile(FormFile importFile) {
		this.importFile = importFile;
	}
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdm要设置的 xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
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
	 * @return the doType
	 */
	public String getDoType() {
		return doType;
	}
	/**
	 * @param doType要设置的 doType
	 */
	public void setDoType(String doType) {
		this.doType = doType;
	}
	/**
	 * @return the editType
	 */
	public String getEditType() {
		return editType;
	}
	/**
	 * @param editType要设置的 editType
	 */
	public void setEditType(String editType) {
		this.editType = editType;
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
	 * @return the fs
	 */
	public String getFs() {
		return fs;
	}
	/**
	 * @param fs要设置的 fs
	 */
	public void setFs(String fs) {
		this.fs = fs;
	}
	/**
	 * @return the lrr
	 */
	public String getLrr() {
		return lrr;
	}
	/**
	 * @param lrr要设置的 lrr
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	/**
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @param lrsj要设置的 lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	/**
	 * @return the xmdms
	 */
	public String getXmdms() {
		return xmdms;
	}
	/**
	 * @param xmdms要设置的 xmdms
	 */
	public void setXmdms(String xmdms) {
		this.xmdms = xmdms;
	}
}
