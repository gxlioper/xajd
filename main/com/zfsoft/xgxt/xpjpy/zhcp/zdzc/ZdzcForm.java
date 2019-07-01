/**
 * @部门:学工产品事业部
 * @日期：2015-5-29 上午11:34:21 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zdzc;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cq [工号:785]
 * @时间： 2015-5-29 上午11:34:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzcForm extends ActionForm {
	
	
	private static final long serialVersionUID = -4392027219234431796L;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private String doType;
	private String editType;		//判断综测项目是编辑模式还是查看模式
	
	private String id;
	private String xh;
	private String xm;
	private String bjdm;
	private String fs;
	private String lrr;
	private String lrsj;
	private String xn;
	private String xq;
	private String xmdm;
	private FormFile importFile;
	private String pmfs;	//排名方式
	private String qxyy;	//取消原因
	private String tjr;	//原提交人
	private String tjsj;	//原提交时间
	private String zczq;	//综测周期
	
	
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
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdm要设置的 bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
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
	 * @return the pmfs
	 */
	public String getPmfs() {
		return pmfs;
	}
	/**
	 * @param pmfs要设置的 pmfs
	 */
	public void setPmfs(String pmfs) {
		this.pmfs = pmfs;
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
	 * @return the tjr
	 */
	public String getTjr() {
		return tjr;
	}
	/**
	 * @param tjr要设置的 tjr
	 */
	public void setTjr(String tjr) {
		this.tjr = tjr;
	}
	/**
	 * @return the tjsj
	 */
	public String getTjsj() {
		return tjsj;
	}
	/**
	 * @param tjsj要设置的 tjsj
	 */
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}
	/**
	 * @return the zczq
	 */
	public String getZczq() {
		return zczq;
	}
	/**
	 * @param zczq要设置的 zczq
	 */
	public void setZczq(String zczq) {
		this.zczq = zczq;
	}
	

}
