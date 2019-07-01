/**
 * @部门:学工产品事业部
 * @日期：2016-3-28 下午05:22:32 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.jg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-3-28 下午05:22:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxddJgForm extends ActionForm {
	   private String xsid;
	   private String xh;
	   private String bjdm;
	   private String xn;
	   private String xq;
	   private String pj;
	   private String py;
	   private String tjsj;
	   private String sjly;
	   private String lylcywid;
	   private String  type;
	   private static final long serialVersionUID = 1L;
	   private SearchModel searchModel = new SearchModel();
	  //导出
	  private ExportModel exportModel = new ExportModel();
	  
	  //徐州医药开学信息设置个性化
	  private String kxrq;
	  private String kkrq;
	  private String jfjzrq;
	  /**
	 * @return the xsid
	 */
	public String getXsid() {
		return xsid;
	}
	/**
	 * @param xsid要设置的 xsid
	 */
	public void setXsid(String xsid) {
		this.xsid = xsid;
	}
	public String getKxrq() {
		return kxrq;
	}
	public void setKxrq(String kxrq) {
		this.kxrq = kxrq;
	}
	public String getKkrq() {
		return kkrq;
	}
	public void setKkrq(String kkrq) {
		this.kkrq = kkrq;
	}
	public String getJfjzrq() {
		return jfjzrq;
	}
	public void setJfjzrq(String jfjzrq) {
		this.jfjzrq = jfjzrq;
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
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdm要设置的 bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xn要设置的 xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @param xq要设置的 xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @return the pj
	 */
	public String getPj() {
		return pj;
	}
	/**
	 * @param pj要设置的 pj
	 */
	public void setPj(String pj) {
		this.pj = pj;
	}
	/**
	 * @return the py
	 */
	public String getPy() {
		return py;
	}
	/**
	 * @param py要设置的 py
	 */
	public void setPy(String py) {
		this.py = py;
	}
	/**
	 * @return the tjsj
	 */
	public String getTjsj() {
		return tjsj;
	}
	/**
	 * @param tjsj要设置的 tjsj
	 */
	public void setTjsj(String tjsj) {
		this.tjsj = tjsj;
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
	 * @return the lylcywid
	 */
	public String getLylcywid() {
		return lylcywid;
	}
	/**
	 * @param lylcywid要设置的 lylcywid
	 */
	public void setLylcywid(String lylcywid) {
		this.lylcywid = lylcywid;
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
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private Pages pages = new Pages();
}
