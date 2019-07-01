/**
 * @部门:学工产品事业部
 * @日期：2016-5-5 上午09:58:45 
 */  
package xsgzgl.rcsw.wsbz.yy;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-5-5 上午09:58:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsbzYyForm extends ActionForm {
	  private String sqid;
	  private String xh;
	  private String fddm;
	  private String yyrq;
	  private String sqsj;
	  private String sqly;
	  private String yyrqday;
	  private String yyrqzc;
	  private String yyzc;
	  private SearchModel searchModel = new SearchModel();
	  private ExportModel exportModel = new ExportModel();
	  /**
	 * @return the yyzc
	 */
	public String getYyzc() {
		return yyzc;
	}
	/**
	 * @param yyzc要设置的 yyzc
	 */
	public void setYyzc(String yyzc) {
		this.yyzc = yyzc;
	}
	
	  /**
	 * @return the yyrqday
	 */
	public String getYyrqday() {
		return yyrqday;
	}
	/**
	 * @param yyrqday要设置的 yyrqday
	 */
	public void setYyrqday(String yyrqday) {
		this.yyrqday = yyrqday;
	}
	/**
	 * @return the yyrqzc
	 */
	public String getYyrqzc() {
		return yyrqzc;
	}
	/**
	 * @param yyrqzc要设置的 yyrqzc
	 */
	public void setYyrqzc(String yyrqzc) {
		this.yyrqzc = yyrqzc;
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
	private String type;
	  private Pages pages = new Pages();
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
	 * @return the fddm
	 */
	public String getFddm() {
		return fddm;
	}
	/**
	 * @param fddm要设置的 fddm
	 */
	public void setFddm(String fddm) {
		this.fddm = fddm;
	}
	/**
	 * @return the yyrq
	 */
	public String getYyrq() {
		return yyrq;
	}
	/**
	 * @param yyrq要设置的 yyrq
	 */
	public void setYyrq(String yyrq) {
		this.yyrq = yyrq;
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
