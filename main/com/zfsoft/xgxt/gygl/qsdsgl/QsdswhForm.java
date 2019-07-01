/**
 * @部门:学工产品事业部
 * @日期：2014-3-19 上午09:39:17 
 */  
package com.zfsoft.xgxt.gygl.qsdsgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理
 * @类功能描述: 寝室导师维护
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-19 上午09:39:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QsdswhForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;

	private String lddm;
	
	private String ch;
	
	private String qsh;
	
	private String dsxm;
	
	private String lxdh;
	
	private String dw;
	
	private String bz;
	
	private String zgh;
	
	private String xn;
	
	private String xq;
	
	private String nd;
	
	private String xqfdyxm;
	
	private String xqfdylxdh;
	
	private String rqkssj;
	
	private String rqjssj;
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
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}

	/**
	 * @param lddm要设置的 lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}

	/**
	 * @param qsh要设置的 qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	/**
	 * @return the dsxm
	 */
	public String getDsxm() {
		return dsxm;
	}

	/**
	 * @param dsxm要设置的 dsxm
	 */
	public void setDsxm(String dsxm) {
		this.dsxm = dsxm;
	}

	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}

	/**
	 * @param lxdh要设置的 lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	/**
	 * @return the dw
	 */
	public String getDw() {
		return dw;
	}

	/**
	 * @param dw要设置的 dw
	 */
	public void setDw(String dw) {
		this.dw = dw;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bz要设置的 bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}

	/**
	 * @return the ch
	 */
	public String getCh() {
		return ch;
	}

	/**
	 * @param ch要设置的 ch
	 */
	public void setCh(String ch) {
		this.ch = ch;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getXqfdyxm() {
		return xqfdyxm;
	}

	public void setXqfdyxm(String xqfdyxm) {
		this.xqfdyxm = xqfdyxm;
	}

	public String getXqfdylxdh() {
		return xqfdylxdh;
	}

	public void setXqfdylxdh(String xqfdylxdh) {
		this.xqfdylxdh = xqfdylxdh;
	}

	/**
	 * @return the rqkssj
	 */
	public String getRqkssj() {
		return rqkssj;
	}

	/**
	 * @param rqkssj要设置的 rqkssj
	 */
	public void setRqkssj(String rqkssj) {
		this.rqkssj = rqkssj;
	}

	/**
	 * @return the rqjssj
	 */
	public String getRqjssj() {
		return rqjssj;
	}

	/**
	 * @param rqjssj要设置的 rqjssj
	 */
	public void setRqjssj(String rqjssj) {
		this.rqjssj = rqjssj;
	}
	
	
}
