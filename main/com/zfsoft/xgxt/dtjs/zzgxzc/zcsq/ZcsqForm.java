/**
 * @部门:学工产品事业部
 * @日期：2017-2-8 上午10:07:49 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.zcsq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-2-8 上午10:07:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcsqForm extends ActionForm {
	 private String sqid;//申请id
	 private String xh;//学号
	 private String szdzb;//所在党支部
	 private String sfsn;//是否省内
	 private String jsdzz;//接收党组织
	 private String sqdw;//所去单位
	 private String dfjzrq;//党费交至日期
	 private String sfkjhyzm;//是否开具婚姻证明
	 private String jsxbh;//介绍信编号
	 private String shzt;//审核状态
	 private String splcid;//审批流程id
	 private String sqr;//申请人
	 private String sqsj;//申请时间
	 
	 private String type;
	 private SearchModel searchModel = new SearchModel();
	 private Pages pages = new Pages();
	 private ExportModel exportModel = new ExportModel();
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
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
	 * @return the szdzb
	 */
	public String getSzdzb() {
		return szdzb;
	}
	/**
	 * @param szdzb要设置的 szdzb
	 */
	public void setSzdzb(String szdzb) {
		this.szdzb = szdzb;
	}
	/**
	 * @return the sfsn
	 */
	public String getSfsn() {
		return sfsn;
	}
	/**
	 * @param sfsn要设置的 sfsn
	 */
	public void setSfsn(String sfsn) {
		this.sfsn = sfsn;
	}
	/**
	 * @return the jsdzz
	 */
	public String getJsdzz() {
		return jsdzz;
	}
	/**
	 * @param jsdzz要设置的 jsdzz
	 */
	public void setJsdzz(String jsdzz) {
		this.jsdzz = jsdzz;
	}
	/**
	 * @return the sqdw
	 */
	public String getSqdw() {
		return sqdw;
	}
	/**
	 * @param sqdw要设置的 sqdw
	 */
	public void setSqdw(String sqdw) {
		this.sqdw = sqdw;
	}
	/**
	 * @return the dfjzrq
	 */
	public String getDfjzrq() {
		return dfjzrq;
	}
	/**
	 * @param dfjzrq要设置的 dfjzrq
	 */
	public void setDfjzrq(String dfjzrq) {
		this.dfjzrq = dfjzrq;
	}
	/**
	 * @return the sfkjhyzm
	 */
	public String getSfkjhyzm() {
		return sfkjhyzm;
	}
	/**
	 * @param sfkjhyzm要设置的 sfkjhyzm
	 */
	public void setSfkjhyzm(String sfkjhyzm) {
		this.sfkjhyzm = sfkjhyzm;
	}
	/**
	 * @return the jsxbh
	 */
	public String getJsxbh() {
		return jsxbh;
	}
	/**
	 * @param jsxbh要设置的 jsxbh
	 */
	public void setJsxbh(String jsxbh) {
		this.jsxbh = jsxbh;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
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
	 * @return the sqr
	 */
	public String getSqr() {
		return sqr;
	}
	/**
	 * @param sqr要设置的 sqr
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
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
	
}
