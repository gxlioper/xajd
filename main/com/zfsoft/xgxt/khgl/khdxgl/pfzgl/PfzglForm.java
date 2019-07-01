/**
 * @部门:学工产品事业部
 * @日期：2015-1-20 上午11:38:01 
 */  
package com.zfsoft.xgxt.khgl.khdxgl.pfzgl;


import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-20 上午11:38:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PfzglForm extends ActionForm{
	private String pfzid; 
	private String pfzmc; 
	private String khdxid;
	private String pflx;
	private String khlx;
	private String khlxmc;
	private String khdxmc;
	private String sfnz;
	private String sczt;
	private String fpzt;
	private String sfqx;//是否全选或多选
	private String xh;
	private String zgh;
	private String sfqy;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	public String getPfzid() {
		return pfzid;
	}
	public void setPfzid(String pfzid) {
		this.pfzid = pfzid;
	}
	public String getPfzmc() {
		return pfzmc;
	}
	public void setPfzmc(String pfzmc) {
		this.pfzmc = pfzmc;
	}
	public String getKhdxid() {
		return khdxid;
	}
	public void setKhdxid(String khdxid) {
		this.khdxid = khdxid;
	}
	public String getPflx() {
		return pflx;
	}
	public void setPflx(String pflx) {
		this.pflx = pflx;
	}
	public String getSfnz() {
		return sfnz;
	}
	public void setSfnz(String sfnz) {
		this.sfnz = sfnz;
	}
	public String getSczt() {
		return sczt;
	}
	public void setSczt(String sczt) {
		this.sczt = sczt;
	}
	public String getFpzt() {
		return fpzt;
	}
	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKhlx() {
		return khlx;
	}
	public void setKhlx(String khlx) {
		this.khlx = khlx;
	}
	public String getSfqx() {
		return sfqx;
	}
	public void setSfqx(String sfqx) {
		this.sfqx = sfqx;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	public String getKhlxmc() {
		return khlxmc;
	}
	public void setKhlxmc(String khlxmc) {
		this.khlxmc = khlxmc;
	}
	public String getKhdxmc() {
		return khdxmc;
	}
	public void setKhdxmc(String khdxmc) {
		this.khdxmc = khdxmc;
	}
	
	

}
