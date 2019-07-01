/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午01:33:14 
 */  
package com.zfsoft.xgxt.xpjpy.cpmd;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优2013版-参评学生名单 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-20 下午01:33:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CpmdModel extends ActionForm {

	
	private static final long serialVersionUID = 1L;

	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private String xh ;		//学号 
	private String xn ;		//学年 
	private String xq ;		//学期 
	private String nj;		// 年级
	private String xydm;	// 学院代码
	private String xymc;	// 学院名称
	private String zydm;	// 专业代码
	private String zymc;	// 专业名称
	private String bjdm;	// 班级代码
	private String bjmc;	// 班级名称
	private String tzhbjdm;	// 调整后班级代码
	

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
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


	public String getXq() {
		return xq;
	}


	public void setXq(String xq) {
		this.xq = xq;
	}


	public String getNj() {
		return nj;
	}


	public void setNj(String nj) {
		this.nj = nj;
	}


	public String getXydm() {
		return xydm;
	}


	public void setXydm(String xydm) {
		this.xydm = xydm;
	}


	public String getXymc() {
		return xymc;
	}


	public void setXymc(String xymc) {
		this.xymc = xymc;
	}


	public String getZydm() {
		return zydm;
	}


	public void setZydm(String zydm) {
		this.zydm = zydm;
	}


	public String getZymc() {
		return zymc;
	}


	public void setZymc(String zymc) {
		this.zymc = zymc;
	}


	public String getBjdm() {
		return bjdm;
	}


	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}


	public String getBjmc() {
		return bjmc;
	}


	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}


	public String getTzhbjdm() {
		return tzhbjdm;
	}


	public void setTzhbjdm(String tzhbjdm) {
		this.tzhbjdm = tzhbjdm;
	}


	
}
