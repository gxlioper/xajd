/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午02:04:57 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.zjsysxhb;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： ChenQ[工号:856]
 * @时间： 2015-6-25 下午02:04:57
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZjsySxhbForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String xh;
	private String jddm;
	private String jdmc;
	private String sjfs;
	private String sjsj;
	private String type;
	private String sxhbid;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();

	
	public String getSxhbid() {
		return sxhbid;
	}

	public void setSxhbid(String sxhbid) {
		this.sxhbid = sxhbid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getJddm() {
		return jddm;
	}

	public void setJddm(String jddm) {
		this.jddm = jddm;
	}

	public String getJdmc() {
		return jdmc;
	}

	public void setJdmc(String jdmc) {
		this.jdmc = jdmc;
	}

	public String getSjfs() {
		return sjfs;
	}

	public void setSjfs(String sjfs) {
		this.sjfs = sjfs;
	}

	public String getSjsj() {
		return sjsj;
	}

	public void setSjsj(String sjsj) {
		this.sjsj = sjsj;
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

}
