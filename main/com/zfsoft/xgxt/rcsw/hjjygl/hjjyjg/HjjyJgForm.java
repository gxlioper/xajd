/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:38:26 
 */  
package com.zfsoft.xgxt.rcsw.hjjygl.hjjyjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2016-5-9 下午02:38:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjjyJgForm extends ActionForm{
	private String jgid;
	private String xn;
	private String xq;
	private String xqmc;
	private String xh;
	private String jyyydm;
	private String jyyymc;
	private String jykssj;
	private String jyjzsj;
	
	private String shzt;
	private String splc;
	private String sqsj;
	private String bz;
	private String jyzt;
	private String ghbz;
	private String sjly;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	public String getJgid() {
		return jgid;
	}
	public void setJgid(String jgid) {
		this.jgid = jgid;
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
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getJyyydm() {
		return jyyydm;
	}
	public void setJyyydm(String jyyydm) {
		this.jyyydm = jyyydm;
	}
	public String getJykssj() {
		return jykssj;
	}
	public void setJykssj(String jykssj) {
		this.jykssj = jykssj;
	}
	public String getJyjzsj() {
		return jyjzsj;
	}
	public void setJyjzsj(String jyjzsj) {
		this.jyjzsj = jyjzsj;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getJyzt() {
		return jyzt;
	}
	public void setJyzt(String jyzt) {
		this.jyzt = jyzt;
	}
	public String getGhbz() {
		return ghbz;
	}
	public void setGhbz(String ghbz) {
		this.ghbz = ghbz;
	}
	public String getSjly() {
		return sjly;
	}
	public void setSjly(String sjly) {
		this.sjly = sjly;
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
	public String getJyyymc() {
		return jyyymc;
	}
	public void setJyyymc(String jyyymc) {
		this.jyyymc = jyyymc;
	}
	

}
