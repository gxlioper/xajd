/**
 * @部门:学工产品事业部
 * @日期：2017-3-21 上午09:30:28 
 */  
package com.zfsoft.xgxt.zjly.zhf.dellog;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 综合分删除日志查询
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： CP[工号:1352]
 * @时间： 2017-3-21 上午09:30:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfdelForm extends ActionForm{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 5821280498417293083L;
	
	
	private String logid;
	private String scrzgh;//删除人工号
	private String scrxm;
	private String scsj;
	private String xh;
	private String jfxmdm;
	private String xmmkdm;
	private String sxsm;
	private String shzt;
	private String cysj;
	private String lrr;
	private String lrrxm;
	private String lrsj;
	
	private String type;
	private SearchModel searchModel = new SearchModel();
	private Pages pages = new Pages();
	private ExportModel exportModel = new ExportModel();
	
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
	 * @return the logid
	 */
	public String getLogid() {
		return logid;
	}
	/**
	 * @param logid要设置的 logid
	 */
	public void setLogid(String logid) {
		this.logid = logid;
	}
	/**
	 * @return the scrzgh
	 */
	public String getScrzgh() {
		return scrzgh;
	}
	/**
	 * @param scrzgh要设置的 scrzgh
	 */
	public void setScrzgh(String scrzgh) {
		this.scrzgh = scrzgh;
	}
	/**
	 * @return the scrxm
	 */
	public String getScrxm() {
		return scrxm;
	}
	/**
	 * @param scrxm要设置的 scrxm
	 */
	public void setScrxm(String scrxm) {
		this.scrxm = scrxm;
	}
	/**
	 * @return the scsj
	 */
	public String getScsj() {
		return scsj;
	}
	/**
	 * @param scsj要设置的 scsj
	 */
	public void setScsj(String scsj) {
		this.scsj = scsj;
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
	 * @return the jfxmdm
	 */
	public String getJfxmdm() {
		return jfxmdm;
	}
	/**
	 * @param jfxmdm要设置的 jfxmdm
	 */
	public void setJfxmdm(String jfxmdm) {
		this.jfxmdm = jfxmdm;
	}
	/**
	 * @return the xmmkdm
	 */
	public String getXmmkdm() {
		return xmmkdm;
	}
	/**
	 * @param xmmkdm要设置的 xmmkdm
	 */
	public void setXmmkdm(String xmmkdm) {
		this.xmmkdm = xmmkdm;
	}
	/**
	 * @return the sxsm
	 */
	public String getSxsm() {
		return sxsm;
	}
	/**
	 * @param sxsm要设置的 sxsm
	 */
	public void setSxsm(String sxsm) {
		this.sxsm = sxsm;
	}
	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @param shzt要设置的 shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @return the cysj
	 */
	public String getCysj() {
		return cysj;
	}
	/**
	 * @param cysj要设置的 cysj
	 */
	public void setCysj(String cysj) {
		this.cysj = cysj;
	}
	/**
	 * @return the lrr
	 */
	public String getLrr() {
		return lrr;
	}
	/**
	 * @param lrr要设置的 lrr
	 */
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	/**
	 * @return the lrrxm
	 */
	public String getLrrxm() {
		return lrrxm;
	}
	/**
	 * @param lrrxm要设置的 lrrxm
	 */
	public void setLrrxm(String lrrxm) {
		this.lrrxm = lrrxm;
	}
	/**
	 * @return the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @param lrsj要设置的 lrsj
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	
	
}
