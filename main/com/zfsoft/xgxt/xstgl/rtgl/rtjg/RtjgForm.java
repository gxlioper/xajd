/**
 * @部门:学工产品事业部
 * @日期：2015-8-7 上午10:35:48 
 */  
package com.zfsoft.xgxt.xstgl.rtgl.rtjg;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-8-7 上午10:35:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RtjgForm extends ActionForm {
	private String  rtid;
	private String  xh;
	private String  stid;
	private String  sqly;
	private String  tc;
	private String  stxmmc;
	private String  xmlbdm;
	private String  id;
	private String  sjly;
	private String  type;
	private String  rtxn;
	private String  rtxq;
	private String  sqsj;
	private String  tnzt;
	private String  rylbdm;
	private static final long serialVersionUID = 1L;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private String[] xhArr;
	/**
	 * @return the rylbdm
	 */
	public String getRylbdm() {
		return rylbdm;
	}
	/**
	 * @param rylbdm要设置的 rylbdm
	 */
	public void setRylbdm(String rylbdm) {
		this.rylbdm = rylbdm;
	}
	/**
	 * @return the tnzt
	 */
	public String getTnzt() {
		return tnzt;
	}
	/**
	 * @param tnzt要设置的 tnzt
	 */
	public void setTnzt(String tnzt) {
		this.tnzt = tnzt;
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
	 * @return the rtxn
	 */
	public String getRtxn() {
		return rtxn;
	}
	/**
	 * @param rtxn要设置的 rtxn
	 */
	public void setRtxn(String rtxn) {
		this.rtxn = rtxn;
	}
	/**
	 * @return the rtxq
	 */
	public String getRtxq() {
		return rtxq;
	}
	/**
	 * @param rtxq要设置的 rtxq
	 */
	public void setRtxq(String rtxq) {
		this.rtxq = rtxq;
	}
	
	/**
	 * @return the xhArr
	 */
	public String[] getXhArr() {
		return xhArr;
	}
	/**
	 * @param xhArr要设置的 xhArr
	 */
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}
	/**
	 * @return the rtid
	 */
	public String getRtid() {
		return rtid;
	}
	/**
	 * @param rtid要设置的 rtid
	 */
	public void setRtid(String rtid) {
		this.rtid = rtid;
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
	 * @return the stid
	 */
	public String getStid() {
		return stid;
	}
	/**
	 * @param stid要设置的 stid
	 */
	public void setStid(String stid) {
		this.stid = stid;
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
	 * @return the tc
	 */
	public String getTc() {
		return tc;
	}
	/**
	 * @param tc要设置的 tc
	 */
	public void setTc(String tc) {
		this.tc = tc;
	}
	/**
	 * @return the stxmmc
	 */
	public String getStxmmc() {
		return stxmmc;
	}
	/**
	 * @param stxmmc要设置的 stxmmc
	 */
	public void setStxmmc(String stxmmc) {
		this.stxmmc = stxmmc;
	}
	/**
	 * @return the xmlbdm
	 */
	public String getXmlbdm() {
		return xmlbdm;
	}
	/**
	 * @param xmlbdm要设置的 xmlbdm
	 */
	public void setXmlbdm(String xmlbdm) {
		this.xmlbdm = xmlbdm;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjly要设置的 sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
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
	

}
