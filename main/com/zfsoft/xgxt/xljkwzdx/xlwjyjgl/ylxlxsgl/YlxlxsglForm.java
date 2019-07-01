/**
 * @部门:学工产品事业部
 * @日期：2014-6-3 上午11:54:44 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.ylxlxsgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-6-3 上午11:54:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YlxlxsglForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -3427252545213781832L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;
	
	private String xh;
	
	private String mtkssj;
	
	private String mtjssj;
	
	private String mtdd;
	
	private String mtzgh;
	
	private String ftnr;
	
	private String lxdm;
	
	private String gzdj;
	
	private String mtxjjjsfdcs;

	private String mtzghmc;
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
	 * @return the mtkssj
	 */
	public String getMtkssj() {
		return mtkssj;
	}

	/**
	 * @param mtkssj要设置的 mtkssj
	 */
	public void setMtkssj(String mtkssj) {
		this.mtkssj = mtkssj;
	}

	/**
	 * @return the mtjssj
	 */
	public String getMtjssj() {
		return mtjssj;
	}

	/**
	 * @param mtjssj要设置的 mtjssj
	 */
	public void setMtjssj(String mtjssj) {
		this.mtjssj = mtjssj;
	}

	/**
	 * @return the mtdd
	 */
	public String getMtdd() {
		return mtdd;
	}

	/**
	 * @param mtdd要设置的 mtdd
	 */
	public void setMtdd(String mtdd) {
		this.mtdd = mtdd;
	}

	/**
	 * @return the mtzgh
	 */
	public String getMtzgh() {
		return mtzgh;
	}

	/**
	 * @param mtzgh要设置的 mtzgh
	 */
	public void setMtzgh(String mtzgh) {
		this.mtzgh = mtzgh;
	}

	/**
	 * @return the ftnr
	 */
	public String getFtnr() {
		return ftnr;
	}

	/**
	 * @param ftnr要设置的 ftnr
	 */
	public void setFtnr(String ftnr) {
		this.ftnr = ftnr;
	}

	/**
	 * @return the lxdm
	 */
	public String getLxdm() {
		return lxdm;
	}

	/**
	 * @param lxdm要设置的 lxdm
	 */
	public void setLxdm(String lxdm) {
		this.lxdm = lxdm;
	}

	/**
	 * @return the gzdj
	 */
	public String getGzdj() {
		return gzdj;
	}

	/**
	 * @param gzdj要设置的 gzdj
	 */
	public void setGzdj(String gzdj) {
		this.gzdj = gzdj;
	}

	/**
	 * @return the mtxjjjsfdcs
	 */
	public String getMtxjjjsfdcs() {
		return mtxjjjsfdcs;
	}

	/**
	 * @param mtxjjjsfdcs要设置的 mtxjjjsfdcs
	 */
	public void setMtxjjjsfdcs(String mtxjjjsfdcs) {
		this.mtxjjjsfdcs = mtxjjjsfdcs;
	}

	/**
	 * @return the mtzghmc
	 */
	public String getMtzghmc() {
		return mtzghmc;
	}

	/**
	 * @param mtzghmc要设置的 mtzghmc
	 */
	public void setMtzghmc(String mtzghmc) {
		this.mtzghmc = mtzghmc;
	}
	
	
}
