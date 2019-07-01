/**
 * @部门:学工产品事业部
 * @日期：2015-6-23 上午08:47:13 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.cssz;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @类功能描述:参数设置
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-23 上午08:47:13 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszModel extends ActionForm {

	private static final long serialVersionUID = -1240429349711890922L;

	private String id;
	private String sqkg;
	private String sqkssj;
	private String sqjssj;
	private String splc;
	/**
	 * 新增字段，为了控制周期
	 */
	private String sqxn;
	private String sqxq;
    private SearchModel searchModel = new SearchModel();//高级查询条件
    private ExportModel exportModel = new ExportModel();//导出
    private Pages pages = new Pages();
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
	private String xn;
    private String xq;
    private String type;
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
	 
	/**
	 * @return the sqxn
	 */
	public String getSqxn() {
		return sqxn;
	}
	/**
	 * @param sqxn要设置的 sqxn
	 */
	public void setSqxn(String sqxn) {
		this.sqxn = sqxn;
	}
	/**
	 * @return the sqxq
	 */
	public String getSqxq() {
		return sqxq;
	}
	/**
	 * @param sqxq要设置的 sqxq
	 */
	public void setSqxq(String sqxq) {
		this.sqxq = sqxq;
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
	 * @return the sqkg
	 */
	public String getSqkg() {
		return sqkg;
	}
	/**
	 * @param sqkg要设置的 sqkg
	 */
	public void setSqkg(String sqkg) {
		this.sqkg = sqkg;
	}
	/**
	 * @return the sqkssj
	 */
	public String getSqkssj() {
		return sqkssj;
	}
	/**
	 * @param sqkssj要设置的 sqkssj
	 */
	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}
	/**
	 * @return the sqjssj
	 */
	public String getSqjssj() {
		return sqjssj;
	}
	/**
	 * @param sqjssj要设置的 sqjssj
	 */
	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
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
	
	
}
