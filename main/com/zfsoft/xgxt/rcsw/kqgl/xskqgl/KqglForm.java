/**
 * @部门:学工产品事业部
 * @日期：2014-6-6 下午04:23:39 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.xskqgl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考勤管理模块
 * @类功能描述: 学生考勤管理 
 * @作者： 陶钢军[工号:1075]
 * @时间： 2014-6-6 下午04:23:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqglForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String kqdjid;  //考勤登记id
	private String xh;  	//学号
	private String xn;  	//学年
	private String xq;  	//学期
	private String zc;  	//周次
	private String kqkc;	//考勤课程
	private String dd; 		//地点
	private String kqlxdm; 	//考勤类型代码
	private String kqsj; 	//考勤时间
	private String kqqk; 	//考勤情况
	private String zjsj; 	//增加时间
	
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
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getKqdjid() {
		return kqdjid;
	}
	public void setKqdjid(String kqdjid) {
		this.kqdjid = kqdjid;
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
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
	}
	public String getKqkc() {
		return kqkc;
	}
	public void setKqkc(String kqkc) {
		this.kqkc = kqkc;
	}
	public String getDd() {
		return dd;
	}
	public void setDd(String dd) {
		this.dd = dd;
	}
	public String getKqlxdm() {
		return kqlxdm;
	}
	public void setKqlxdm(String kqlxdm) {
		this.kqlxdm = kqlxdm;
	}
	public String getKqsj() {
		return kqsj;
	}
	public void setKqsj(String kqsj) {
		this.kqsj = kqsj;
	}
	public String getKqqk() {
		return kqqk;
	}
	public void setKqqk(String kqqk) {
		this.kqqk = kqqk;
	}
	public String getZjsj() {
		return zjsj;
	}
	public void setZjsj(String zjsj) {
		this.zjsj = zjsj;
	}
	
}
