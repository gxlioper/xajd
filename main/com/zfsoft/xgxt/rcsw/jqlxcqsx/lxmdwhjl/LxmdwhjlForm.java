/**
 * @部门:学工产品事业部
 * @日期：2017年3月27日 下午1:51:04 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwhjl;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 周末假期离校管理模块
 * @类功能描述: 离校名单维护记录Form
 * @作者： xuwen[工号:1426]
 * @时间： 2017年3月27日 下午1:51:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxmdwhjlForm extends ActionForm{
	
	private static final long serialVersionUID = 4504656165427919558L;
	
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	private SearchModel searchModel = new SearchModel();
	
	private String jlid;		//离校名单维护记录id
	private String czr;			//操作人
	private String czsj;		//操作时间
	private String czlx;		//操作类型
	private String xh;			//学号
	private String xm;			//姓名
	private String xmid;		//留校项目id
	private String xgqlxqksm;	//修改前留校情况说明
	private String xghlxqksm;	//修改后留校情况说明
	private String czxq;		//操作详情
	
	
	/**
	 * @return the jlid
	 */
	public String getJlid() {
		return jlid;
	}
	/**
	 * @param jlid要设置的 jlid
	 */
	public void setJlid(String jlid) {
		this.jlid = jlid;
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
	 * @return the czr
	 */
	public String getCzr() {
		return czr;
	}
	/**
	 * @param czr要设置的 czr
	 */
	public void setCzr(String czr) {
		this.czr = czr;
	}
	/**
	 * @return the czsj
	 */
	public String getCzsj() {
		return czsj;
	}
	/**
	 * @param czsj要设置的 czsj
	 */
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}
	/**
	 * @return the czlx
	 */
	public String getCzlx() {
		return czlx;
	}
	/**
	 * @param czlx要设置的 czlx
	 */
	public void setCzlx(String czlx) {
		this.czlx = czlx;
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
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xm要设置的 xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the xmid
	 */
	public String getXmid() {
		return xmid;
	}
	/**
	 * @param xmid要设置的 xmid
	 */
	public void setXmid(String xmid) {
		this.xmid = xmid;
	}
	/**
	 * @return the xgqlxqksm
	 */
	public String getXgqlxqksm() {
		return xgqlxqksm;
	}
	/**
	 * @param xgqlxqksm要设置的 xgqlxqksm
	 */
	public void setXgqlxqksm(String xgqlxqksm) {
		this.xgqlxqksm = xgqlxqksm;
	}
	/**
	 * @return the xghlxqksm
	 */
	public String getXghlxqksm() {
		return xghlxqksm;
	}
	/**
	 * @param xghlxqksm要设置的 xghlxqksm
	 */
	public void setXghlxqksm(String xghlxqksm) {
		this.xghlxqksm = xghlxqksm;
	}
	/**
	 * @return the czxq
	 */
	public String getCzxq() {
		return czxq;
	}
	/**
	 * @param czxq要设置的 czxq
	 */
	public void setCzxq(String czxq) {
		this.czxq = czxq;
	}
	
	
	
	
}
