/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-24 ����04:55:04 
 */  
package com.zfsoft.xgxt.xsxx.cxpy;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(�����������) 
 * @���ߣ� CMJ [���ţ�913]
 * @ʱ�䣺 2013-7-24 ����04:55:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxpyForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String pk;
	private String xn;
	private String xq;
	private String xqmc;
	private String cxdjdm;
	private String cxdj;
	private String cxpy;
	private String xhs;
	private String xh;
	private String xm;
	private String nj;
	private String xymc;
	private String zymc;
	private String bjmc;
	private String cxdjmc;
	private String bzr;
	private String sjly;
	private String sqsj;
	
	
	
	
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
	 * @return the cxdj
	 */
	public String getCxdj() {
		return cxdj;
	}
	/**
	 * @param cxdjҪ���õ� cxdj
	 */
	public void setCxdj(String cxdj) {
		this.cxdj = cxdj;
	}
	/**
	 * @return the cxdjmc
	 */
	public String getCxdjmc() {
		return cxdjmc;
	}
	/**
	 * @param cxdjmcҪ���õ� cxdjmc
	 */
	public void setCxdjmc(String cxdjmc) {
		this.cxdjmc = cxdjmc;
	}
	/**
	 * @return the pk
	 */
	public String getPk() {
		return pk;
	}
	/**
	 * @param pkҪ���õ� pk
	 */
	public void setPk(String pk) {
		this.pk = pk;
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
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the nj
	 */
	public String getNj() {
		return nj;
	}
	/**
	 * @param njҪ���õ� nj
	 */
	public void setNj(String nj) {
		this.nj = nj;
	}
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
	 * @return the zymc
	 */
	public String getZymc() {
		return zymc;
	}
	/**
	 * @param zymcҪ���õ� zymc
	 */
	public void setZymc(String zymc) {
		this.zymc = zymc;
	}
	/**
	 * @return the bjmc
	 */
	public String getBjmc() {
		return bjmc;
	}
	/**
	 * @param bjmcҪ���õ� bjmc
	 */
	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}
	/**
	 * @return the xhs
	 */
	public String getXhs() {
		return xhs;
	}
	/**
	 * @param xhsҪ���õ� xhs
	 */
	public void setXhs(String xhs) {
		this.xhs = xhs;
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
	 * @return the cxdjdm
	 */
	public String getCxdjdm() {
		return cxdjdm;
	}
	/**
	 * @param cxdjdmҪ���õ� cxdjdm
	 */
	public void setCxdjdm(String cxdjdm) {
		this.cxdjdm = cxdjdm;
	}
	/**
	 * @return the cxpy
	 */
	public String getCxpy() {
		return cxpy;
	}
	/**
	 * @param cxpyҪ���õ� cxpy
	 */
	public void setCxpy(String cxpy) {
		this.cxpy = cxpy;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param typeҪ���õ� type
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
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setBzr(String bzr) {
		this.bzr = bzr;
	}
	public String getBzr() {
		return bzr;
	}
	public String getSqsj() {
		return sqsj;
	}
	public void setSqsj(String sqsj) {
		this.sqsj = sqsj;
	}
}
