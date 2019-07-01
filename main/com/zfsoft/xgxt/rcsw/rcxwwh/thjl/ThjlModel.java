/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午11:26:25 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.thjl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 日常行为--谈话记录
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-12-1 下午01:35:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ThjlModel extends ActionForm {

	
	private static final long serialVersionUID = 1966791940187986544L;
	
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	
	private String thid;
	private String xh;
	private String xn;
	private String xq;
	private String thsj;
	private String thnr;
	private String fjmc;
	private String thjs;
	private String xqmc;
	private String jsxm;
	
	
	
	/**
	 * @return the jsxm
	 */
	public String getJsxm() {
		return jsxm;
	}
	/**
	 * @param jsxm要设置的 jsxm
	 */
	public void setJsxm(String jsxm) {
		this.jsxm = jsxm;
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
	 * @return the thid
	 */
	public String getThid() {
		return thid;
	}
	/**
	 * @param thid要设置的 thid
	 */
	public void setThid(String thid) {
		this.thid = thid;
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
	 * @return the thsj
	 */
	public String getThsj() {
		return thsj;
	}
	/**
	 * @param thsj要设置的 thsj
	 */
	public void setThsj(String thsj) {
		this.thsj = thsj;
	}
	/**
	 * @return the thnr
	 */
	public String getThnr() {
		return thnr;
	}
	/**
	 * @param thnr要设置的 thnr
	 */
	public void setThnr(String thnr) {
		this.thnr = thnr;
	}
	/**
	 * @return the fjmc
	 */
	public String getFjmc() {
		return fjmc;
	}
	/**
	 * @param fjmc要设置的 fjmc
	 */
	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}
	/**
	 * @return the thjs
	 */
	public String getThjs() {
		return thjs;
	}
	/**
	 * @param thjs要设置的 thjs
	 */
	public void setThjs(String thjs) {
		this.thjs = thjs;
	}
	
	
}
