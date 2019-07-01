/**
 * @部门:学工产品事业部
 * @日期：2016-12-22 上午11:07:36 
 */  
package com.zfsoft.xgxt.xlzx.zxzxjl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-12-22 上午11:07:36 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxzxjlxxModel extends ActionForm{
	
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String bh;
	private String id;
	private String xh;
	private String zxsxm;
	private String zxsj;
	private String zxdd;
	private String zxpg;
	private String zxgc;
	private String zxfk;
	private String zxth;
	
	private SearchModel searchModel = new SearchModel();
	//自定义导出
	private ExportModel exportModel = new ExportModel();
	private String type;
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
	 * @return the zxsxm
	 */
	public String getZxsxm() {
		return zxsxm;
	}
	/**
	 * @param zxsxm要设置的 zxsxm
	 */
	public void setZxsxm(String zxsxm) {
		this.zxsxm = zxsxm;
	}
	/**
	 * @return the zxsj
	 */
	public String getZxsj() {
		return zxsj;
	}
	/**
	 * @param zxsj要设置的 zxsj
	 */
	public void setZxsj(String zxsj) {
		this.zxsj = zxsj;
	}
	/**
	 * @return the zxdd
	 */
	public String getZxdd() {
		return zxdd;
	}
	/**
	 * @param zxdd要设置的 zxdd
	 */
	public void setZxdd(String zxdd) {
		this.zxdd = zxdd;
	}
	/**
	 * @return the zxpg
	 */
	public String getZxpg() {
		return zxpg;
	}
	/**
	 * @param zxpg要设置的 zxpg
	 */
	public void setZxpg(String zxpg) {
		this.zxpg = zxpg;
	}
	/**
	 * @return the zxgc
	 */
	public String getZxgc() {
		return zxgc;
	}
	/**
	 * @param zxgc要设置的 zxgc
	 */
	public void setZxgc(String zxgc) {
		this.zxgc = zxgc;
	}
	/**
	 * @return the zxfk
	 */
	public String getZxfk() {
		return zxfk;
	}
	/**
	 * @param zxfk要设置的 zxfk
	 */
	public void setZxfk(String zxfk) {
		this.zxfk = zxfk;
	}
	/**
	 * @return the zxth
	 */
	public String getZxth() {
		return zxth;
	}
	/**
	 * @param zxth要设置的 zxth
	 */
	public void setZxth(String zxth) {
		this.zxth = zxth;
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
	 * @return the bh
	 */
	public String getBh() {
		return bh;
	}
	/**
	 * @param bh要设置的 bh
	 */
	public void setBh(String bh) {
		this.bh = bh;
	}
	
	
}
