/**
 * @部门:学工产品事业部
 * @日期：2014-3-7 下午02:47:25 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.jggl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-7 下午02:47:25 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzmJgForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : -5338289385430930034L
	 */ 
	
	private static final long serialVersionUID = -5338289385430930034L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;
	
	private String zdzmjgid;
	
	private String xh ;
	
	private String sqsj;
	
	private String sqly;
	
	private String jrsj;
	
	private String sjly;
	
	private String zdzmsqid;

	
	
	/**
	 * @描述 ：无参数构造器
	 */
	public ZdzmJgForm() {
		super();
	}

	/**
	 * @return the zdzmjgid
	 */
	public String getZdzmjgid() {
		return zdzmjgid;
	}

	/**
	 * @param zdzmjgid要设置的 zdzmjgid
	 */
	public void setZdzmjgid(String zdzmjgid) {
		this.zdzmjgid = zdzmjgid;
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
	 * @return the jrsj
	 */
	public String getJrsj() {
		return jrsj;
	}

	/**
	 * @param jrsj要设置的 jrsj
	 */
	public void setJrsj(String jrsj) {
		this.jrsj = jrsj;
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
	 * @return the zdzmsqid
	 */
	public String getZdzmsqid() {
		return zdzmsqid;
	}

	/**
	 * @param zdzmsqid要设置的 zdzmsqid
	 */
	public void setZdzmsqid(String zdzmsqid) {
		this.zdzmsqid = zdzmsqid;
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
	
}
