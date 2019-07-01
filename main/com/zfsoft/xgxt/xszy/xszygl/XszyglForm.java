/**
 * @部门:学工产品事业部
 * @日期：2015-2-11 上午11:26:02 
 */  
package com.zfsoft.xgxt.xszy.xszygl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-2-11 上午11:26:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XszyglForm extends ActionForm{
	private String id;
	private String zgh;
	private String nj;
	private String xm;
	private String xb;
	private String lddm;
	private String qsh;
	private String zwzc;//职务职称
	private String dwdm; //单位
	private String lxdh;//联系电话
	private String zzmmdm;//政治面貌
	private String dzyx;//电子邮箱
	private String dlyq;//大类要求
	private String bz;//
	private String kyxbj;//跨院系标记
	private String bjyx;//标记院系
	private String type;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getZwzc() {
		return zwzc;
	}
	public void setZwzc(String zwzc) {
		this.zwzc = zwzc;
	}
	public String getDwdm() {
		return dwdm;
	}
	public void setDwdm(String dwdm) {
		this.dwdm = dwdm;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getZzmmdm() {
		return zzmmdm;
	}
	public void setZzmmdm(String zzmmdm) {
		this.zzmmdm = zzmmdm;
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	public String getDlyq() {
		return dlyq;
	}
	public void setDlyq(String dlyq) {
		this.dlyq = dlyq;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getKyxbj() {
		return kyxbj;
	}
	public void setKyxbj(String kyxbj) {
		this.kyxbj = kyxbj;
	}
	public String getBjyx() {
		return bjyx;
	}
	public void setBjyx(String bjyx) {
		this.bjyx = bjyx;
	}
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
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	
	

}
