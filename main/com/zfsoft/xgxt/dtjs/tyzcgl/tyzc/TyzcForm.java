
package com.zfsoft.xgxt.dtjs.tyzcgl.tyzc;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * 团员注册
 */
public class TyzcForm extends ActionForm{

	private static final long serialVersionUID = 1L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String type;
	
	
	private String id; //标识符
	
	private String xh;	//学号
	
	private String xn;	//学年
	
	private String zczt; //注册状态
	private String zcztmc; //注册状态
	
	private String zcsj;//注册时间
	
	private String zcr;//注册人
	
	private String pk;//
	private String pks;//
	
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
	 * @return the zczt
	 */
	public String getZczt() {
		return zczt;
	}

	/**
	 * @param zczt要设置的 zczt
	 */
	public void setZczt(String zczt) {
		this.zczt = zczt;
	}
	
	/**
	 * @return the zcztmc
	 */
	public String getZcztmc() {
		return zcztmc;
	}

	/**
	 * @param zcztmc要设置的 zcztmc
	 */
	public void setZcztmc(String zcztmc) {
		this.zcztmc = zcztmc;
	}

	/**
	 * @return the zcsj
	 */
	public String getZcsj() {
		return zcsj;
	}

	/**
	 * @param zcsj要设置的 zcsj
	 */
	public void setZcsj(String zcsj) {
		this.zcsj = zcsj;
	}

	/**
	 * @return the zcr
	 */
	public String getZcr() {
		return zcr;
	}

	/**
	 * @param zcr要设置的 zcr
	 */
	public void setZcr(String zcr) {
		this.zcr = zcr;
	}

	/**
	 * @return the pk
	 */
	public String getPk() {
		return pk;
	}

	/**
	 * @param pk要设置的 pk
	 */
	public void setPk(String pk) {
		this.pk = pk;
	}

	/**
	 * @return the pks
	 */
	public String getPks() {
		return pks;
	}

	/**
	 * @param pks要设置的 pks
	 */
	public void setPks(String pks) {
		this.pks = pks;
	}

}
