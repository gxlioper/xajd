/**
 * @部门:学工产品事业部
 * @日期：2016-7-22 上午10:40:54 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.sq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-22 上午10:40:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TttzxmForm extends ActionForm {
	  private String  ttsqid;
	  private String  xmdm;                    
	  private String  sqr;
	  private String  sqsj;
	  private String  sqly;
	  private String  splc;
	  private String  shzt;                    
	  private String  tdmc;
	  private String  dzxh;
	  private String  xhs;
	  private String  ttcyid;
	  private String[] xhArr;
	  private SearchModel searchModel = new SearchModel();
		 // private static final long serialVersionUID = 1L;
	  private String type;
	  private ExportModel exportModel = new ExportModel();
	  private Pages pages = new Pages();
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
	 * @return the ttcyid
	 */
	public String getTtcyid() {
		return ttcyid;
	}
	/**
	 * @param ttcyid要设置的 ttcyid
	 */
	public void setTtcyid(String ttcyid) {
		this.ttcyid = ttcyid;
	}
	
    /**
	 * @return the xhs
	 */
	public String getXhs() {
		return xhs;
	}
	/**
	 * @param xhs要设置的 xhs
	 */
	public void setXhs(String xhs) {
		this.xhs = xhs;
	}
	
	/**
	 * @return the ttsqid
	 */
	public String getTtsqid() {
		return ttsqid;
	}
	/**
	 * @param ttsqid要设置的 ttsqid
	 */
	public void setTtsqid(String ttsqid) {
		this.ttsqid = ttsqid;
	}
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdm要设置的 xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
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
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}
	/**
	 * @param splc要设置的 splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
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
	 * @return the tdmc
	 */
	public String getTdmc() {
		return tdmc;
	}
	/**
	 * @param tdmc要设置的 tdmc
	 */
	public void setTdmc(String tdmc) {
		this.tdmc = tdmc;
	}
	/**
	 * @return the dzxh
	 */
	public String getDzxh() {
		return dzxh;
	}
	/**
	 * @param dzxh要设置的 dzxh
	 */
	public void setDzxh(String dzxh) {
		this.dzxh = dzxh;
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
