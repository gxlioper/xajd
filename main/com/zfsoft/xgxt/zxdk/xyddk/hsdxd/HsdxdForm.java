/**
 * @部门:学工产品事业部
 * @日期：2016-11-15 下午03:06:07 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.hsdxd;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-11-15 下午03:06:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HsdxdForm extends ActionForm {
	private String id;
	private String htbh;
	private String xdxn;
	private String xdje;
	private String xdly;
	private String sqsj;
	private String splc;
	private String shzt;
	private String xh;
	private String xn;
	private String fkje;
	private String fksj;
	private String fkpzh;
	private String dkzh;
	private String khh;
	private String fkzt;
	private String xq;
	private String type;
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages(); 
	private static final long serialVersionUID = 1L;
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
	 * @return the fkje
	 */
	public String getFkje() {
		return fkje;
	}
	/**
	 * @param fkje要设置的 fkje
	 */
	public void setFkje(String fkje) {
		this.fkje = fkje;
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
	 * @return the fkpzh
	 */
	public String getFkpzh() {
		return fkpzh;
	}
	/**
	 * @param fkpzh要设置的 fkpzh
	 */
	public void setFkpzh(String fkpzh) {
		this.fkpzh = fkpzh;
	}
	/**
	 * @return the dkzh
	 */
	public String getDkzh() {
		return dkzh;
	}
	/**
	 * @param dkzh要设置的 dkzh
	 */
	public void setDkzh(String dkzh) {
		this.dkzh = dkzh;
	}
	/**
	 * @return the khh
	 */
	public String getKhh() {
		return khh;
	}
	/**
	 * @param khh要设置的 khh
	 */
	public void setKhh(String khh) {
		this.khh = khh;
	}
	/**
	 * @return the fkzt
	 */
	public String getFkzt() {
		return fkzt;
	}
	/**
	 * @param fkzt要设置的 fkzt
	 */
	public void setFkzt(String fkzt) {
		this.fkzt = fkzt;
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
	 * @return the htbh
	 */
	public String getHtbh() {
		return htbh;
	}
	/**
	 * @param htbh要设置的 htbh
	 */
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	/**
	 * @return the xdxn
	 */
	public String getXdxn() {
		return xdxn;
	}
	/**
	 * @param xdxn要设置的 xdxn
	 */
	public void setXdxn(String xdxn) {
		this.xdxn = xdxn;
	}
	/**
	 * @return the xdje
	 */
	public String getXdje() {
		return xdje;
	}
	/**
	 * @param xdje要设置的 xdje
	 */
	public void setXdje(String xdje) {
		this.xdje = xdje;
	}
	/**
	 * @return the xdly
	 */
	public String getXdly() {
		return xdly;
	}
	/**
	 * @param xdly要设置的 xdly
	 */
	public void setXdly(String xdly) {
		this.xdly = xdly;
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
