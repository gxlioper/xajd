/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-19 ����01:59:15 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.zpjg;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @�๦������: �������
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-19 ����01:59:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZpjgModel extends ActionForm{

	private static final long serialVersionUID = -4427242453796115424L;

	
	private String id ;
	private String xh ;
	private String xn ;
	private String xq ;
	private String djdm ;
	private String zpnr ;
	private String pysj ;
	private String sjlyywid ;
	private String sjly ;
	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String xqmc;
	private String djmc;
	private String bjdm;
	private String xydm;
	private String xymc;
	
	
	/**
	 * @return the xymc
	 */
	public String getXymc() {
		return xymc;
	}
	/**
	 * @param xymcҪ���õ� xymc
	 */
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	/**
	 * @return the xydm
	 */
	public String getXydm() {
		return xydm;
	}
	/**
	 * @param xydmҪ���õ� xydm
	 */
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	/**
	 * @return the bjdm
	 */
	public String getBjdm() {
		return bjdm;
	}
	/**
	 * @param bjdmҪ���õ� bjdm
	 */
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}
	/**
	 * @param xqmcҪ���õ� xqmc
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
	 * @param djmcҪ���õ� djmc
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
	 * @param idҪ���õ� id
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
	 * @param xhҪ���õ� xh
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
	 * @param xnҪ���õ� xn
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
	 * @param xqҪ���õ� xq
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
	 * @param djdmҪ���õ� djdm
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
	 * @param zpnrҪ���õ� zpnr
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
	 * @param pysjҪ���õ� pysj
	 */
	public void setPysj(String pysj) {
		this.pysj = pysj;
	}
	/**
	 * @return the sjlyywid
	 */
	public String getSjlyywid() {
		return sjlyywid;
	}
	/**
	 * @param sjlyywidҪ���õ� sjlyywid
	 */
	public void setSjlyywid(String sjlyywid) {
		this.sjlyywid = sjlyywid;
	}
	/**
	 * @return the sjly
	 */
	public String getSjly() {
		return sjly;
	}
	/**
	 * @param sjlyҪ���õ� sjly
	 */
	public void setSjly(String sjly) {
		this.sjly = sjly;
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
	
	
}
