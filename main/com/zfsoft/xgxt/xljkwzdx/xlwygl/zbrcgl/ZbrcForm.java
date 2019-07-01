/**
 * @部门:学工产品事业部
 * @日期：2014-5-26 上午11:51:41 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.zbrcgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 周报日程
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-26 上午11:51:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZbrcForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private String zbid;
	
	private String zblx;
	
	private String zbzc;
	
	private String zbksrq;
	
	private String zbjsrq;
	
	private String xn;
	
	private String xq;
	
	private String czsj;

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
	 * @return the zbid
	 */
	public String getZbid() {
		return zbid;
	}

	/**
	 * @param zbid要设置的 zbid
	 */
	public void setZbid(String zbid) {
		this.zbid = zbid;
	}

	/**
	 * @return the zblx
	 */
	public String getZblx() {
		return zblx;
	}

	/**
	 * @param zblx要设置的 zblx
	 */
	public void setZblx(String zblx) {
		this.zblx = zblx;
	}

	/**
	 * @return the zbzc
	 */
	public String getZbzc() {
		return zbzc;
	}

	/**
	 * @param zbzc要设置的 zbzc
	 */
	public void setZbzc(String zbzc) {
		this.zbzc = zbzc;
	}

	/**
	 * @return the zbksrq
	 */
	public String getZbksrq() {
		return zbksrq;
	}

	/**
	 * @param zbksrq要设置的 zbksrq
	 */
	public void setZbksrq(String zbksrq) {
		this.zbksrq = zbksrq;
	}

	/**
	 * @return the zbjsrq
	 */
	public String getZbjsrq() {
		return zbjsrq;
	}

	/**
	 * @param zbjsrq要设置的 zbjsrq
	 */
	public void setZbjsrq(String zbjsrq) {
		this.zbjsrq = zbjsrq;
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

	public String getCzsj() {
		return czsj;
	}

	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}
}
