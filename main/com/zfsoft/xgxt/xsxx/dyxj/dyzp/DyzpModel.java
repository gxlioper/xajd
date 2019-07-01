/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 上午11:08:33 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.dyzp;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @类功能描述: 德育自评  
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-19 上午11:08:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DyzpModel extends SuperAuditModel {

	private static final long serialVersionUID = 2316939303013825371L;

	private String id ;
	private String xh ;
	private String xn ;
	private String xq ;
	private String djdm ;
	private String zpnr ;
	private String pysj ;
    private String splcid ;
    private String shzt ;
    private String type ;
    private String splc;
    private String xtgwid;
    private String pddjdm;
    
    private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String xqmc;
	private String djmc;
	
	private String[] sqid;
	private String[] splcids;
	private String[] xtgwids;
	private String[] xhs;
	
	
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
	 * @return the djmc
	 */
	public String getDjmc() {
		return djmc;
	}
	/**
	 * @param djmc要设置的 djmc
	 */
	public void setDjmc(String djmc) {
		this.djmc = djmc;
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
	 * @return the djdm
	 */
	public String getDjdm() {
		return djdm;
	}
	/**
	 * @param djdm要设置的 djdm
	 */
	public void setDjdm(String djdm) {
		this.djdm = djdm;
	}
	/**
	 * @return the zpnr
	 */
	public String getZpnr() {
		return zpnr;
	}
	/**
	 * @param zpnr要设置的 zpnr
	 */
	public void setZpnr(String zpnr) {
		this.zpnr = zpnr;
	}
	/**
	 * @return the pysj
	 */
	public String getPysj() {
		return pysj;
	}
	/**
	 * @param pysj要设置的 pysj
	 */
	public void setPysj(String pysj) {
		this.pysj = pysj;
	}
	/**
	 * @return the splcid
	 */
	public String getSplcid() {
		return splcid;
	}
	/**
	 * @param splcid要设置的 splcid
	 */
	public void setSplcid(String splcid) {
		this.splcid = splcid;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String[] getSqid() {
		return sqid;
	}
	public void setSqid(String[] sqid) {
		this.sqid = sqid;
	}
	public String[] getXhs() {
		return xhs;
	}
	public void setXhs(String[] xhs) {
		this.xhs = xhs;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}
	public String[] getSplcids() {
		return splcids;
	}
	public void setSplcids(String[] splcids) {
		this.splcids = splcids;
	}
	public String getXtgwid() {
		return xtgwid;
	}
	public void setXtgwid(String xtgwid) {
		this.xtgwid = xtgwid;
	}
	public String[] getXtgwids() {
		return xtgwids;
	}
	public void setXtgwids(String[] xtgwids) {
		this.xtgwids = xtgwids;
	}
	public String getPddjdm() {
		return pddjdm;
	}
	public void setPddjdm(String pddjdm) {
		this.pddjdm = pddjdm;
	}
	
	
}
