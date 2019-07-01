/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-4 ����10:14:19 
 */  
package com.zfsoft.xgxt.gygl.wsjc.wsf;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @�๦������: �����ֹ��� 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-4 ����10:14:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
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
	 * @param exportModelҪ���õ� exportModel
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
	 * @param importFileҪ���õ� importFile
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
	 * @param fslxҪ���õ� fslx
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
	 * @param xmdmҪ���õ� xmdm
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
	 * @param rcidҪ���õ� rcid
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
	 * @param jcdxҪ���õ� jcdx
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
	 * @param lddmҪ���õ� lddm
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
	 * @param qshҪ���õ� qsh
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
	 * @param cwhҪ���õ� cwh
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
	 * @param jcrqҪ���õ� jcrq
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
	 * @param jcbmҪ���õ� jcbm
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
	 * @param jcryҪ���õ� jcry
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
	 * @param wsfҪ���õ� wsf
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
	 * @param wsdjҪ���õ� wsdj
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
	 * @param bzҪ���õ� bz
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
	 * @param lrrҪ���õ� lrr
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
	 * @param lrsjҪ���õ� lrsj
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
	 * @param pfbzҪ���õ� pfbz
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
	 * @param pagesҪ���õ� pages
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
	 * @param searchModelҪ���õ� searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	
	
}
