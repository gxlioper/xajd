/**
 * @部门:学工产品事业部
 * @日期：2013-7-23 下午01:34:40 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 综测分数 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-23 下午01:34:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcfsModel extends ActionForm {

	
	private static final long serialVersionUID = 1L;
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
	private String zcyf;//综测月份
	private String xmdms; // 上海戏剧学院个性化
	private String cxlx;
	
	/**
	 * @return the cxlx
	 */
	public String getCxlx() {
		return cxlx;
	}
	/**
	 * @param cxlx要设置的 cxlx
	 */
	public void setCxlx(String cxlx) {
		this.cxlx = cxlx;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getDoType() {
		return doType;
	}
	public void setDoType(String doType) {
		this.doType = doType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getFs() {
		return fs;
	}
	public void setFs(String fs) {
		this.fs = fs;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public String getLrsj() {
		return lrsj;
	}
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
	}
	public String getXmdm() {
		return xmdm;
	}
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}
	public FormFile getImportFile() {
		return importFile;
	}
	public void setImportFile(FormFile importFile) {
		this.importFile = importFile;
	}
	public String getPmfs() {
		return pmfs;
	}
	public void setPmfs(String pmfs) {
		this.pmfs = pmfs;
	}
	public String getQxyy() {
		return qxyy;
	}
	public void setQxyy(String qxyy) {
		this.qxyy = qxyy;
	}
	public String getTjr() {
		return tjr;
	}
	public void setTjr(String tjr) {
		this.tjr = tjr;
	}
	public String getTjsj() {
		return tjsj;
	}
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
	}
	public String getZczq() {
		return zczq;
	}
	public void setZczq(String zczq) {
		this.zczq = zczq;
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
	public String getZcyf() {
		return zcyf;
	}
	public void setZcyf(String zcyf) {
		this.zcyf = zcyf;
	}
	public String getXmdms() {
		return xmdms;
	}
	public void setXmdms(String xmdms) {
		this.xmdms = xmdms;
	}
	
}
