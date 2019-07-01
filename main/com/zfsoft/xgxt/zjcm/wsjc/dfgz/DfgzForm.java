/**
 * @部门:学工产品事业部
 * @日期：2016-3-2 上午08:58:42 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.dfgz;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 传媒卫生分
 * @类功能描述: 打分规则
 * @作者： cq [工号:785]
 * @时间： 2016-3-2 上午08:58:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DfgzForm extends ActionForm {

	private static final long serialVersionUID = -1353497542272030712L;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	private String dfszid;
	private String xn;
	private String xq;
	private String ccny;
	private String wwsj;
	private String pfzid;
	private String ccbl;
	private String bhbyb;
	private String wwzzsj;
	private String dfzid;
	private String xqmc;
	private String tjzt;
	
	private List<HashMap<String, String>> pfzList = null;	//评分组list
	private List<HashMap<String, String>> pfzszList = null; //评分组设置list
	
	
	
	
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
	 * @return the dfszid
	 */
	public String getDfszid() {
		return dfszid;
	}
	/**
	 * @param dfszid要设置的 dfszid
	 */
	public void setDfszid(String dfszid) {
		this.dfszid = dfszid;
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
	 * @return the ccny
	 */
	public String getCcny() {
		return ccny;
	}
	/**
	 * @param ccny要设置的 ccny
	 */
	public void setCcny(String ccny) {
		this.ccny = ccny;
	}
	/**
	 * @return the wwsj
	 */
	public String getWwsj() {
		return wwsj;
	}
	/**
	 * @param wwsj要设置的 wwsj
	 */
	public void setWwsj(String wwsj) {
		this.wwsj = wwsj;
	}
	/**
	 * @return the pfzid
	 */
	public String getPfzid() {
		return pfzid;
	}
	/**
	 * @param pfzid要设置的 pfzid
	 */
	public void setPfzid(String pfzid) {
		this.pfzid = pfzid;
	}
	/**
	 * @return the ccbl
	 */
	public String getCcbl() {
		return ccbl;
	}
	/**
	 * @param ccbl要设置的 ccbl
	 */
	public void setCcbl(String ccbl) {
		this.ccbl = ccbl;
	}
	/**
	 * @return the bhbyb
	 */
	public String getBhbyb() {
		return bhbyb;
	}
	/**
	 * @param bhbyb要设置的 bhbyb
	 */
	public void setBhbyb(String bhbyb) {
		this.bhbyb = bhbyb;
	}
	/**
	 * @return the wwzzsj
	 */
	public String getWwzzsj() {
		return wwzzsj;
	}
	/**
	 * @param wwzzsj要设置的 wwzzsj
	 */
	public void setWwzzsj(String wwzzsj) {
		this.wwzzsj = wwzzsj;
	}
	/**
	 * @return the pfzList
	 */
	public List<HashMap<String, String>> getPfzList() {
		return pfzList;
	}
	/**
	 * @param pfzList要设置的 pfzList
	 */
	public void setPfzList(List<HashMap<String, String>> pfzList) {
		this.pfzList = pfzList;
	}
	/**
	 * @return the pfzszList
	 */
	public List<HashMap<String, String>> getPfzszList() {
		return pfzszList;
	}
	/**
	 * @param pfzszList要设置的 pfzszList
	 */
	public void setPfzszList(List<HashMap<String, String>> pfzszList) {
		this.pfzszList = pfzszList;
	}
	/**
	 * @return the dfzid
	 */
	public String getDfzid() {
		return dfzid;
	}
	/**
	 * @param dfzid要设置的 dfzid
	 */
	public void setDfzid(String dfzid) {
		this.dfzid = dfzid;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmc要设置的 xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}
	/**
	 * @return the tjzt
	 */
	public String getTjzt() {
		return tjzt;
	}
	/**
	 * @param tjzt要设置的 tjzt
	 */
	public void setTjzt(String tjzt) {
		this.tjzt = tjzt;
	}
	
	
}
