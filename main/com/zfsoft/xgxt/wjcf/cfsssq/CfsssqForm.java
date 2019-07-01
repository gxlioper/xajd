/**
 * @部门:学工产品事业部
 * @日期：2013-10-29 下午01:47:44 
 */  
package com.zfsoft.xgxt.wjcf.cfsssq;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分申诉申请) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-29 下午01:46:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfsssqForm extends ActionForm {

	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String ssid;            //申诉ID
	private String cfid;            //处分ID
	private String sqsj;            //申诉申请时间
	private String sqly;            //申请理由
	private String ssjg;            //申诉结果
	private String fjmc;            //附件名称
	private String ssfilepath;       
	private FormFile fj;            //附件
	private String splcid;          //审批流程id
	
	private String returnflag;       //退回标志
	
	private String xh;
	
	
	
	/**
	 * @return the ssid
	 */
	public String getSsid() {
		return ssid;
	}
	/**
	 * @param ssid要设置的 ssid
	 */
	public void setSsid(String ssid) {
		this.ssid = ssid;
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
	 * @return the cfid
	 */
	public String getCfid() {
		return cfid;
	}
	/**
	 * @param cfid要设置的 cfid
	 */
	public void setCfid(String cfid) {
		this.cfid = cfid;
	}
	/**
	 * @return the sqsj
	 */
	public String getSqsj() {
		return sqsj;
	}
	/**
	 * @param sqsj要设置的 sqsj
	 */
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
	/**
	 * @return the sqly
	 */
	public String getSqly() {
		return sqly;
	}
	/**
	 * @param sqly要设置的 sqly
	 */
	public void setSqly(String sqly) {
		this.sqly = sqly;
	}
	/**
	 * @return the ssjg
	 */
	public String getSsjg() {
		return ssjg;
	}
	/**
	 * @param ssjg要设置的 ssjg
	 */
	public void setSsjg(String ssjg) {
		this.ssjg = ssjg;
	}
	/**
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * @param fjmc要设置的 fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	/**
	 * @return the ssfilepath
	 */
	public String getSsfilepath() {
		return ssfilepath;
	}
	/**
	 * @param ssfilepath要设置的 ssfilepath
	 */
	public void setSsfilepath(String ssfilepath) {
		this.ssfilepath = ssfilepath;
	}
	/**
	 * @return the fj
	 */
	public FormFile getFj() {
		return fj;
	}
	/**
	 * @param fj要设置的 fj
	 */
	public void setFj(FormFile fj) {
		this.fj = fj;
	}
	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcid要设置的 splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	/**
	 * @return the returnflag
	 */
	public String getReturnflag() {
		return returnflag;
	}
	/**
	 * @param returnflag要设置的 returnflag
	 */
	public void setReturnflag(String returnflag) {
		this.returnflag = returnflag;
	}

	
	
}
