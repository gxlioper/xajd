/**
 * @部门:学工产品事业部
 * @日期：2015-6-4 上午10:14:19 
 */  
package com.zfsoft.xgxt.gygl.wsjc.wsf;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @类功能描述: 卫生分管理 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-4 上午10:14:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsfModel extends ActionForm {

	private static final long serialVersionUID = -5542855224167652861L;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String rcid;
	private String jcdx;
	private String lddm;
	private String qsh;
	private String cwh;
	private String jcrq;
	private String jcbm;
	private String jcry;
	private String wsf;
    private String wsdj;
    private String bz;
    private String lrr;
    private String lrsj;
    private String pfbz;
    private String xmdm;
    private String fslx;
    private FormFile importFile;
    
    
	
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
	 * @return the importFile
	 */
	public FormFile getImportFile() {
		return importFile;
	}
	/**
	 * @param importFile要设置的 importFile
	 */
	public void setImportFile(FormFile importFile) {
		this.importFile = importFile;
	}
	/**
	 * @return the fslx
	 */
	public String getFslx() {
		return fslx;
	}
	/**
	 * @param fslx要设置的 fslx
	 */
	public void setFslx(String fslx) {
		this.fslx = fslx;
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
	 * @return the rcid
	 */
	public String getRcid() {
		return rcid;
	}
	/**
	 * @param rcid要设置的 rcid
	 */
	public void setRcid(String rcid) {
		this.rcid = rcid;
	}
	/**
	 * @return the jcdx
	 */
	public String getJcdx() {
		return jcdx;
	}
	/**
	 * @param jcdx要设置的 jcdx
	 */
	public void setJcdx(String jcdx) {
		this.jcdx = jcdx;
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
	 * @return the cwh
	 */
	public String getCwh() {
		return cwh;
	}
	/**
	 * @param cwh要设置的 cwh
	 */
	public void setCwh(String cwh) {
		this.cwh = cwh;
	}
	/**
	 * @return the jcrq
	 */
	public String getJcrq() {
		return jcrq;
	}
	/**
	 * @param jcrq要设置的 jcrq
	 */
	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
	}
	/**
	 * @return the jcbm
	 */
	public String getJcbm() {
		return jcbm;
	}
	/**
	 * @param jcbm要设置的 jcbm
	 */
	public void setJcbm(String jcbm) {
		this.jcbm = jcbm;
	}
	/**
	 * @return the jcry
	 */
	public String getJcry() {
		return jcry;
	}
	/**
	 * @param jcry要设置的 jcry
	 */
	public void setJcry(String jcry) {
		this.jcry = jcry;
	}
	/**
	 * @return the wsf
	 */
	public String getWsf() {
		return wsf;
	}
	/**
	 * @param wsf要设置的 wsf
	 */
	public void setWsf(String wsf) {
		this.wsf = wsf;
	}
	/**
	 * @return the wsdj
	 */
	public String getWsdj() {
		return wsdj;
	}
	/**
	 * @param wsdj要设置的 wsdj
	 */
	public void setWsdj(String wsdj) {
		this.wsdj = wsdj;
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
	/**
	 * @return the pfbz
	 */
	public String getPfbz() {
		return pfbz;
	}
	/**
	 * @param pfbz要设置的 pfbz
	 */
	public void setPfbz(String pfbz) {
		this.pfbz = pfbz;
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
	
	
}
