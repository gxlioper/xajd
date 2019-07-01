/**
 * @部门:学工产品事业部
 * @日期：2015-9-21 上午10:46:53 
 */  
package com.zfsoft.xgxt.rcsw.txhd.hdkhgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-9-21 上午10:46:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class hdkhForm extends ActionForm {
	private String  xh;
	private String  hdxmbh;
	private String  hjqk;
	private String  sfhdxf;
	private String  type;
	private String  xn;
	private String  xq;
	private String  id;
	private String  lbdm;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	/**
	 * @return the lbdm
	 */
	public String getLbdm() {
		return lbdm;
	}
	/**
	 * @param lbdm要设置的 lbdm
	 */
	public void setLbdm(String lbdm) {
		this.lbdm = lbdm;
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
	 * @return the hdxmbh
	 */
	public String getHdxmbh() {
		return hdxmbh;
	}
	/**
	 * @param hdxmbh要设置的 hdxmbh
	 */
	public void setHdxmbh(String hdxmbh) {
		this.hdxmbh = hdxmbh;
	}
	/**
	 * @return the hjqk
	 */
	public String getHjqk() {
		return hjqk;
	}
	/**
	 * @param hjqk要设置的 hjqk
	 */
	public void setHjqk(String hjqk) {
		this.hjqk = hjqk;
	}
	/**
	 * @return the sfhdxf
	 */
	public String getSfhdxf() {
		return sfhdxf;
	}
	/**
	 * @param sfhdxf要设置的 sfhdxf
	 */
	public void setSfhdxf(String sfhdxf) {
		this.sfhdxf = sfhdxf;
	}	
}
