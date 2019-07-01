/**
 * @部门:学工产品事业部
 * @日期：2016-5-9 上午10:45:07 
 */  
package com.zfsoft.xgxt.rcsw.hjjygl.hjjysq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-5-9 上午10:45:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjjySqForm extends ActionForm{
	private String sqid;
	private String xn;
	private String xq;
	private String xqmc;
	private String xh;
	private String jyyydm;
	private String jykssj;
	private String jyjzsj;
	
	private String shzt;
	private String splc;
	private String sqsj;
	private String bz;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
	public String getSqid() {
		return sqid;
	}
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	public String getXqmc() {
		return xqmc;
	}
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	
	

}
