/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 上午11:02:09 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsflr;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-8 上午11:02:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class WsflrForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String wsfid;
	private String ssxq;
	private String lddm;
	private String ldmc;
	private String qsh;
	private String qsmc;
	private String pfzid;
	private String dfszid;
	private String ccny;
	private String ccr;
	private String ccrq;
	private String ccrip;
	private String fz;
	private String fzbz;
	private String ssxy;
	private String tjzt;
	private String xn;
	private String xq;
	private String[] ids;
	private String nj;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private String type;
	
	/**
	 * @return the wsfid
	 */
	public String getWsfid() {
		return wsfid;
	}
	/**
	 * @param wsfid要设置的 wsfid
	 */
	public void setWsfid(String wsfid) {
		this.wsfid = wsfid;
	}
	/**
	 * @return the ssxq
	 */
	public String getSsxq() {
		return ssxq;
	}
	/**
	 * @param ssxq要设置的 ssxq
	 */
	public void setSsxq(String ssxq) {
		this.ssxq = ssxq;
	}
	/**
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}
	/**
	 * @param lddm要设置的 lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	/**
	 * @return the ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmc要设置的 ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @param qsh要设置的 qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	/**
	 * @return the qsmc
	 */
	public String getQsmc() {
		return qsmc;
	}
	/**
	 * @param qsmc要设置的 qsmc
	 */
	public void setQsmc(String qsmc) {
		this.qsmc = qsmc;
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
	 * @return the ccr
	 */
	public String getCcr() {
		return ccr;
	}
	/**
	 * @param ccr要设置的 ccr
	 */
	public void setCcr(String ccr) {
		this.ccr = ccr;
	}
	/**
	 * @return the ccrq
	 */
	public String getCcrq() {
		return ccrq;
	}
	/**
	 * @param ccrq要设置的 ccrq
	 */
	public void setCcrq(String ccrq) {
		this.ccrq = ccrq;
	}
	/**
	 * @return the fz
	 */
	public String getFz() {
		return fz;
	}
	/**
	 * @param fz要设置的 fz
	 */
	public void setFz(String fz) {
		this.fz = fz;
	}
	/**
	 * @return the fzbz
	 */
	public String getFzbz() {
		return fzbz;
	}
	/**
	 * @param fzbz要设置的 fzbz
	 */
	public void setFzbz(String fzbz) {
		this.fzbz = fzbz;
	}
	/**
	 * @return the ssxy
	 */
	public String getSsxy() {
		return ssxy;
	}
	/**
	 * @param ssxy要设置的 ssxy
	 */
	public void setSsxy(String ssxy) {
		this.ssxy = ssxy;
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
	 * @return the ccrip
	 */
	public String getCcrip() {
		return ccrip;
	}
	/**
	 * @param ccrip要设置的 ccrip
	 */
	public void setCcrip(String ccrip) {
		this.ccrip = ccrip;
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
	
	
	
}
