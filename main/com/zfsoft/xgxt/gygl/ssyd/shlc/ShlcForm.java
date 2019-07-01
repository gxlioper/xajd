/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:00:24 
 */
package com.zfsoft.xgxt.gygl.ssyd.shlc;

import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;


/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 宿舍异动
 * @类功能描述:form
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:00:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ShlcForm extends ActionForm {
	private String id;
	private String splcid;
	private String tssplcid;
	private String sstzsplcid;
	private String sxlssplcid;
	private String rzsplcid;
	private String type;
	private String zsfxs;
	
	private List<String[]> paramList;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String ssydlx;//宿舍异动类型
	
	/**
	 * @return the ssydlx
	 */
	public String getSsydlx() {
		return ssydlx;
	}
	/**
	 * @param ssydlx要设置的 ssydlx
	 */
	public void setSsydlx(String ssydlx) {
		this.ssydlx = ssydlx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSplcid() {
		return splcid;
	}
	public void setSplcid(String splcid) {
		this.splcid = splcid;
	}
	/**
	 * @return the tssplcid
	 */
	public String getTssplcid() {
		return tssplcid;
	}
	/**
	 * @param tssplcid要设置的 tssplcid
	 */
	public void setTssplcid(String tssplcid) {
		this.tssplcid = tssplcid;
	}
	/**
	 * @return the sstzsplcid
	 */
	public String getSstzsplcid() {
		return sstzsplcid;
	}
	/**
	 * @param sstzsplcid要设置的 sstzsplcid
	 */
	public void setSstzsplcid(String sstzsplcid) {
		this.sstzsplcid = sstzsplcid;
	}
	/**
	 * @return the sxlssplcid
	 */
	public String getSxlssplcid() {
		return sxlssplcid;
	}
	/**
	 * @param sxlssplcid要设置的 sxlssplcid
	 */
	public void setSxlssplcid(String sxlssplcid) {
		this.sxlssplcid = sxlssplcid;
	}
	/**
	 * @return the rzsplcid
	 */
	public String getRzsplcid() {
		return rzsplcid;
	}
	/**
	 * @param rzsplcid要设置的 rzsplcid
	 */
	public void setRzsplcid(String rzsplcid) {
		this.rzsplcid = rzsplcid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getZsfxs() {
		return zsfxs;
	}
	public void setZsfxs(String zsfxs) {
		this.zsfxs = zsfxs;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the paramList
	 */
	public List<String[]> getParamList() {
		return paramList;
	}
	/**
	 * @param paramList要设置的 paramList
	 */
	public void setParamList(List<String[]> paramList) {
		this.paramList = paramList;
	}
	
}