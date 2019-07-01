/**
 * @部门:学工产品事业部
 * @日期：2013-5-13 上午09:05:03 
 */  
package com.zfsoft.xgxt.rwgl.rwtw;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 人武管理模块
 * @类功能描述: TODO入伍学费补偿管理
 * @作者：HongLin 
 * @时间： 2013-5-13 上午08:55:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RwxfbcglForm extends ActionForm{

private static final long serialVersionUID = 1L;
	
	private String type;//类型
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String guid ;//ID
	private String xh ;//学号
	private String xn ;//学年
	private String xfbcsj ;//学费补偿时间
	private String xfbcje ;//学费补偿金额
	private String bz ;//备注
	private String[] id;//id数组
	private String[] xhs;//学号数组
	private ExportModel exportModel = new ExportModel();
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXfbcsj() {
		return xfbcsj;
	}
	public void setXfbcsj(String xfbcsj) {
		this.xfbcsj = xfbcsj;
	}
	public String getXfbcje() {
		return xfbcje;
	}
	public void setXfbcje(String xfbcje) {
		this.xfbcje = xfbcje;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String[] getId() {
		return id;
	}
	public void setId(String[] id) {
		this.id = id;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
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
	
}
