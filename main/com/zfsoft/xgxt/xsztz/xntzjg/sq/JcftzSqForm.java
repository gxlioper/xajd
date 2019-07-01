/**
 * @部门:学工产品事业部
 * @日期：2016-3-28 下午05:03:41 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.sq;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-28 下午05:03:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcftzSqForm extends ActionForm{
	private String way;
	private String cxmc;
	private String[] ids;
	private String[] xmdms;
    private String[] csms;
    private String sqid;
	private String jgid;
	private String xmdm;
	private String xh;
	private String xm;
	private String tzhjcf;
	private String jxdm;
	private String sfqq;
	private String type;
	private String rdsplc;
	private String xfrdsqzt;
	private SearchModel searchModel = new SearchModel();
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private String bz1;
    private String bz2;
    private String bz3;
    private String bz4;
    private String bz5;
    
	/**
	 * @return the bz1
	 */
	public String getBz1() {
		return bz1;
	}
	/**
	 * @param bz1要设置的 bz1
	 */
	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}
	/**
	 * @return the bz2
	 */
	public String getBz2() {
		return bz2;
	}
	/**
	 * @param bz2要设置的 bz2
	 */
	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}
	/**
	 * @return the bz3
	 */
	public String getBz3() {
		return bz3;
	}
	/**
	 * @param bz3要设置的 bz3
	 */
	public void setBz3(String bz3) {
		this.bz3 = bz3;
	}
	/**
	 * @return the bz4
	 */
	public String getBz4() {
		return bz4;
	}
	/**
	 * @param bz4要设置的 bz4
	 */
	public void setBz4(String bz4) {
		this.bz4 = bz4;
	}
	/**
	 * @return the bz5
	 */
	public String getBz5() {
		return bz5;
	}
	/**
	 * @param bz5要设置的 bz5
	 */
	public void setBz5(String bz5) {
		this.bz5 = bz5;
	}
	/**
	 * @return the csms
	 */
	public String[] getCsms() {
		return csms;
	}
	/**
	 * @param csms要设置的 csms
	 */
	public void setCsms(String[] csms) {
		this.csms = csms;
	}
	/**
	 * @return the sqid
	 */
	public String getSqid() {
		return sqid;
	}
	/**
	 * @param sqid要设置的 sqid
	 */
	public void setSqid(String sqid) {
		this.sqid = sqid;
	}
	/**
	 * @return the xmdm
	 */
	public String getXmdm() {
		return xmdm;
	}
	/**
	 * @param xmdm要设置的 xmdm
	 */
	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
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
	 * @return the tzhjcf
	 */
	public String getTzhjcf() {
		return tzhjcf;
	}
	/**
	 * @param tzhjcf要设置的 tzhjcf
	 */
	public void setTzhjcf(String tzhjcf) {
		this.tzhjcf = tzhjcf;
	}
	/**
	 * @return the jxdm
	 */
	public String getJxdm() {
		return jxdm;
	}
	/**
	 * @param jxdm要设置的 jxdm
	 */
	public void setJxdm(String jxdm) {
		this.jxdm = jxdm;
	}
	/**
	 * @return the sfqq
	 */
	public String getSfqq() {
		return sfqq;
	}
	/**
	 * @param sfqq要设置的 sfqq
	 */
	public void setSfqq(String sfqq) {
		this.sfqq = sfqq;
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
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @param jgid要设置的 jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
	}
	/**
	 * @return the ids
	 */
	public String[] getIds() {
		return ids;
	}
	/**
	 * @param ids要设置的 ids
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	/**
	 * @return the rdsplc
	 */
	public String getRdsplc() {
		return rdsplc;
	}
	/**
	 * @param rdsplc要设置的 rdsplc
	 */
	public void setRdsplc(String rdsplc) {
		this.rdsplc = rdsplc;
	}
	/**
	 * @return the xfrdsqzt
	 */
	public String getXfrdsqzt() {
		return xfrdsqzt;
	}
	/**
	 * @param xfrdsqzt要设置的 xfrdsqzt
	 */
	public void setXfrdsqzt(String xfrdsqzt) {
		this.xfrdsqzt = xfrdsqzt;
	}
	/**
	 * @return the cxmc
	 */
	public String getCxmc() {
		return cxmc;
	}
	/**
	 * @param cxmc要设置的 cxmc
	 */
	public void setCxmc(String cxmc) {
		this.cxmc = cxmc;
	}
	/**
	 * @return the xmdms
	 */
	public String[] getXmdms() {
		return xmdms;
	}
	/**
	 * @param xmdms要设置的 xmdms
	 */
	public void setXmdms(String[] xmdms) {
		this.xmdms = xmdms;
	}
	/**
	 * @return the way
	 */
	public String getWay() {
		return way;
	}
	/**
	 * @param way要设置的 way
	 */
	public void setWay(String way) {
		this.way = way;
	}
	
	
	
	
	
	
	
	
    
    
}
