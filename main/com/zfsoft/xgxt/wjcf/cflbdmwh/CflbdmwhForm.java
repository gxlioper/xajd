/**
 * @部门:学工产品事业部
 * @日期：2013-10-24 上午10:52:35 
 */  
package com.zfsoft.xgxt.wjcf.cflbdmwh;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分类别代码维护) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-24 上午10:52:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CflbdmwhForm extends ActionForm {

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	
	private String jsslqsr;            //解除受理起始日
	private String cflbdm;            //处分类别代码
	private String cflbmc;            //处分类别名称
	private String sfszcfqx;  		  //是否设置处分期限0否1是
	private String cfqx;			//处分期限
	private String qxnsfkzz; 	    //期限内是否可终止0否1是
	private String spl;            //审批流
	private String sfkss;            //是否可申诉
	private String sfksqjc;            //是否可申请解除
	private String ssslgzr;            //申诉受理工作日
	
	private String cffwqx;	//处分发文权限

	private String cjsj;//创建时间

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
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
	 * @return the jsslqsr
	 */
	public String getJsslqsr() {
		return jsslqsr;
	}
	/**
	 * @param jsslqsr要设置的 jsslqsr
	 */
	public void setJsslqsr(String jsslqsr) {
		this.jsslqsr = jsslqsr;
	}
	/**
	 * @return the cflbdm
	 */
	public String getCflbdm() {
		return cflbdm;
	}
	/**
	 * @param cflbdm要设置的 cflbdm
	 */
	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}
	/**
	 * @return the cflbmc
	 */
	public String getCflbmc() {
		return cflbmc;
	}
	/**
	 * @param cflbmc要设置的 cflbmc
	 */
	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}
	/**
	 * @return the spl
	 */
	public String getSpl() {
		return spl;
	}
	/**
	 * @param spl要设置的 spl
	 */
	public void setSpl(String spl) {
		this.spl = spl;
	}
	/**
	 * @return the sfkss
	 */
	public String getSfkss() {
		return sfkss;
	}
	/**
	 * @param sfkss要设置的 sfkss
	 */
	public void setSfkss(String sfkss) {
		this.sfkss = sfkss;
	}
	/**
	 * @return the sfksqjc
	 */
	public String getSfksqjc() {
		return sfksqjc;
	}
	/**
	 * @param sfksqjc要设置的 sfksqjc
	 */
	public void setSfksqjc(String sfksqjc) {
		this.sfksqjc = sfksqjc;
	}
	/**
	 * @return the ssslgzr
	 */
	public String getSsslgzr() {
		return ssslgzr;
	}
	/**
	 * @param ssslgzr要设置的 ssslgzr
	 */
	public void setSsslgzr(String ssslgzr) {
		this.ssslgzr = ssslgzr;
	}
	
	
	/**
	 * @return the cfqx
	 */
	public String getCfqx() {
		return cfqx;
	}
	/**
	 * @param cfqx要设置的 cfqx
	 */
	public void setCfqx(String cfqx) {
		this.cfqx = cfqx;
	}
	/**
	 * @return the sfszcfqx
	 */
	public String getSfszcfqx() {
		return sfszcfqx;
	}
	/**
	 * @param sfszcfqx要设置的 sfszcfqx
	 */
	public void setSfszcfqx(String sfszcfqx) {
		this.sfszcfqx = sfszcfqx;
	}
	/**
	 * @return the qxnsfkzz
	 */
	public String getQxnsfkzz() {
		return qxnsfkzz;
	}
	/**
	 * @param qxnsfkzz要设置的 qxnsfkzz
	 */
	public void setQxnsfkzz(String qxnsfkzz) {
		this.qxnsfkzz = qxnsfkzz;
	}
	/**
	 * @return the cffwqx
	 */
	public String getCffwqx() {
		return cffwqx;
	}
	/**
	 * @param cffwqx要设置的 cffwqx
	 */
	public void setCffwqx(String cffwqx) {
		this.cffwqx = cffwqx;
	}

	
	
}
