/**
 * @部门:学工产品事业部
 * @日期：2014-6-3 上午11:54:44 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.xlfdjl;

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

public class XlfdjlForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -3427252545213781832L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;
	
	private String fdid;
	
	private String xh;
	
	private String zgh;
	
	private String zfdyxm;
	
	private String sj;
	
	private String lx;
	
	private String fdlxdm;
	
	private String thnrfdcs;

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
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}

	/**
	 * @param zgh要设置的 zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	/**
	 * @return the sj
	 */
	public String getSj() {
		return sj;
	}

	/**
	 * @param sj要设置的 sj
	 */
	public void setSj(String sj) {
		this.sj = sj;
	}

	/**
	 * @return the lx
	 */
	public String getLx() {
		return lx;
	}

	/**
	 * @param lx要设置的 lx
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}

	/**
	 * @return the fdlxdm
	 */
	public String getFdlxdm() {
		return fdlxdm;
	}

	/**
	 * @param fdlxdm要设置的 fdlxdm
	 */
	public void setFdlxdm(String fdlxdm) {
		this.fdlxdm = fdlxdm;
	}

	/**
	 * @return the thnrfdcs
	 */
	public String getThnrfdcs() {
		return thnrfdcs;
	}

	/**
	 * @param thnrfdcs要设置的 thnrfdcs
	 */
	public void setThnrfdcs(String thnrfdcs) {
		this.thnrfdcs = thnrfdcs;
	}

	/**
	 * @return the fdid
	 */
	public String getFdid() {
		return fdid;
	}

	/**
	 * @param fdid要设置的 fdid
	 */
	public void setFdid(String fdid) {
		this.fdid = fdid;
	}

	/**
	 * @return the zfdyxm
	 */
	public String getZfdyxm() {
		return zfdyxm;
	}

	/**
	 * @param zfdyxm要设置的 zfdyxm
	 */
	public void setZfdyxm(String zfdyxm) {
		this.zfdyxm = zfdyxm;
	}
	
	
}
