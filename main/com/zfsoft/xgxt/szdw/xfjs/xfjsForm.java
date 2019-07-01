/**
 * @部门:学工产品事业部
 * @日期：2017-8-9 下午03:43:00 
 */  
package com.zfsoft.xgxt.szdw.xfjs;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： CP[工号:1352]
 * @时间： 2017-8-9 下午03:43:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class xfjsForm extends ActionForm{
	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String xn;
	private String xq;
	private String zgh;
	private String fdy;
	private String ydxsrs;
	private String sjcqrs;
	private String jcsj;
	private String jcjc;
	private String lrr;
	private String bz;
	private String pycc;
	private String pyccmc;
	private String yxdm;
	private String yxmc;
	private String xqmc;
	private String bjdm;
	private String bjmc;
	private String zydm;
	private String zymc;
	private String xydm;
	private String xymc;
	private String nj;
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	/**
	 * @return the yxmc
	 */
	public String getYxmc() {
		return yxmc;
	}
	/**
	 * @param yxmc要设置的 yxmc
	 */
	public void setYxmc(String yxmc) {
		this.yxmc = yxmc;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the fdy
	 */
	public String getFdy() {
		return fdy;
	}
	/**
	 * @param fdy要设置的 fdy
	 */
	public void setFdy(String fdy) {
		this.fdy = fdy;
	}
	/**
	 * @return the ydxsrs
	 */
	public String getYdxsrs() {
		return ydxsrs;
	}
	/**
	 * @param ydxsrs要设置的 ydxsrs
	 */
	public void setYdxsrs(String ydxsrs) {
		this.ydxsrs = ydxsrs;
	}
	/**
	 * @return the sjcqrs
	 */
	public String getSjcqrs() {
		return sjcqrs;
	}
	/**
	 * @param sjcqrs要设置的 sjcqrs
	 */
	public void setSjcqrs(String sjcqrs) {
		this.sjcqrs = sjcqrs;
	}
	/**
	 * @return the jcsj
	 */
	public String getJcsj() {
		return jcsj;
	}
	/**
	 * @param jcsj要设置的 jcsj
	 */
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	/**
	 * @return the jcjc
	 */
	public String getJcjc() {
		return jcjc;
	}
	/**
	 * @param jcjc要设置的 jcjc
	 */
	public void setJcjc(String jcjc) {
		this.jcjc = jcjc;
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
	 * @return the pycc
	 */
	public String getPycc() {
		return pycc;
	}
	/**
	 * @param pycc要设置的 pycc
	 */
	public void setPycc(String pycc) {
		this.pycc = pycc;
	}
	/**
	 * @return the pyccmc
	 */
	public String getPyccmc() {
		return pyccmc;
	}
	/**
	 * @param pyccmc要设置的 pyccmc
	 */
	public void setPyccmc(String pyccmc) {
		this.pyccmc = pyccmc;
	}
	/**
	 * @return the yxdm
	 */
	public String getYxdm() {
		return yxdm;
	}
	/**
	 * @param yxdm要设置的 yxdm
	 */
	public void setYxdm(String yxdm) {
		this.yxdm = yxdm;
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
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdm要设置的 bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmc要设置的 bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the zydm
	 */
	public String getZydm() {
		return zydm;
	}
	/**
	 * @param zydm要设置的 zydm
	 */
	public void setZydm(String zydm) {
		this.zydm = zydm;
	}
	/**
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * @param zymc要设置的 zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @param xydm要设置的 xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymc要设置的 xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param nj要设置的 nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
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
	

}
