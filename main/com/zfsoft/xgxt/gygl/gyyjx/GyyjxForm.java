/**
 * @部门:学工产品事业部
 * @日期：2014-3-24 上午11:31:10 
 */  
package com.zfsoft.xgxt.gygl.gyyjx;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-24 上午11:31:10 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyyjxForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();

	private String type;
	
	private String yjfldm; //意见分类代码
	
	private String yjflmc; //意见分类名称
	
	
	private String gyyjid; //意见id
	
	private String xh;//学号
	
	private String yjms;//意见描述
	
	private String yjsj; //意见时间
	
	private String fkqk;//反馈情况
	
	private String fknr;//反馈内容
	
	private String fkr;//反馈人
	
	private String fksj;//反馈时间

	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * @param pages要设置的 pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}

	/**
	 * @param searchModel要设置的 searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
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

	/**
	 * @return the yjfldm
	 */
	public String getYjfldm() {
		return yjfldm;
	}

	/**
	 * @param yjfldm要设置的 yjfldm
	 */
	public void setYjfldm(String yjfldm) {
		this.yjfldm = yjfldm;
	}

	/**
	 * @return the yjflmc
	 */
	public String getYjflmc() {
		return yjflmc;
	}

	/**
	 * @param yjflmc要设置的 yjflmc
	 */
	public void setYjflmc(String yjflmc) {
		this.yjflmc = yjflmc;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the gyyjid
	 */
	public String getGyyjid() {
		return gyyjid;
	}

	/**
	 * @param gyyjid要设置的 gyyjid
	 */
	public void setGyyjid(String gyyjid) {
		this.gyyjid = gyyjid;
	}

	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}

	/**
	 * @param xh要设置的 xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/**
	 * @return the yjms
	 */
	public String getYjms() {
		return yjms;
	}

	/**
	 * @param yjms要设置的 yjms
	 */
	public void setYjms(String yjms) {
		this.yjms = yjms;
	}

	/**
	 * @return the fkqk
	 */
	public String getFkqk() {
		return fkqk;
	}

	/**
	 * @param fkqk要设置的 fkqk
	 */
	public void setFkqk(String fkqk) {
		this.fkqk = fkqk;
	}

	/**
	 * @return the fknr
	 */
	public String getFknr() {
		return fknr;
	}

	/**
	 * @param fknr要设置的 fknr
	 */
	public void setFknr(String fknr) {
		this.fknr = fknr;
	}

	/**
	 * @return the fkr
	 */
	public String getFkr() {
		return fkr;
	}

	/**
	 * @param fkr要设置的 fkr
	 */
	public void setFkr(String fkr) {
		this.fkr = fkr;
	}

	/**
	 * @return the fksj
	 */
	public String getFksj() {
		return fksj;
	}

	/**
	 * @param fksj要设置的 fksj
	 */
	public void setFksj(String fksj) {
		this.fksj = fksj;
	}

	/**
	 * @return the yjsj
	 */
	public String getYjsj() {
		return yjsj;
	}

	/**
	 * @param yjsj要设置的 yjsj
	 */
	public void setYjsj(String yjsj) {
		this.yjsj = yjsj;
	}
	
	
	
}
