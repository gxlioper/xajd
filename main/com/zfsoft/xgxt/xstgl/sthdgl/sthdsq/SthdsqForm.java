/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午05:02:37 
 */  
package com.zfsoft.xgxt.xstgl.sthdgl.sthdsq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-9 下午05:02:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SthdsqForm extends ActionForm {
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;

	private String hdid;
/*	private String xn;
	private String xq;
	private String xqmc;*/
	private String xh;
	private String hdmc;//活动名称
	private String fwsj;//服务时间
	private String fwsc;//服务时长
	private String fwdd;//服务地点
	private String zbdw;//主办单位
	private String fjid;
	private String splc;
	private String shzt;
	private String fwddssx;//服务地点省市县
	private String lrr;
	private String lrsj;

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

	public String getHdid() {
		return hdid;
	}

	public void setHdid(String hdid) {
		this.hdid = hdid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getHdmc() {
		return hdmc;
	}

	public void setHdmc(String hdmc) {
		this.hdmc = hdmc;
	}

	public String getFwsj() {
		return fwsj;
	}

	public void setFwsj(String fwsj) {
		this.fwsj = fwsj;
	}

	public String getFwsc() {
		return fwsc;
	}

	public void setFwsc(String fwsc) {
		this.fwsc = fwsc;
	}

	public String getFwdd() {
		return fwdd;
	}

	public void setFwdd(String fwdd) {
		this.fwdd = fwdd;
	}

	public String getZbdw() {
		return zbdw;
	}

	public void setZbdw(String zbdw) {
		this.zbdw = zbdw;
	}

	public String getFjid() {
		return fjid;
	}

	public void setFjid(String fjid) {
		this.fjid = fjid;
	}

	public String getSplc() {
		return splc;
	}

	public void setSplc(String splc) {
		this.splc = splc;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getFwddssx() {
		return fwddssx;
	}

	public void setFwddssx(String fwddssx) {
		this.fwddssx = fwddssx;
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
}
