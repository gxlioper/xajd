/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-2-6 ����04:37:40 
 */  
package com.zfsoft.xgxt.xszy.xsqshf;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-2-6 ����04:37:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XszyQshfForm extends ActionForm{
	
	private String id;
	private String lddm;
	private String qsh;
	private String nj;
	private String xydm;//ѧ԰����
	private String xymc;//ѧ԰����
	private String ssyxdm;
	private String ssyxmc;
	private String qsxb;
	private String dl;//����
	private String rzrs;//��ס����
	private String sfhhqs;
	private String fpzt;
	private String czsj;
	private String fpczr;
	private String type;
	private String thxy;
	private String thr;

	
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	public String getQsh() {
		return qsh;
	}
	public void setQsh(String qsh) {
		this.qsh = qsh;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getXymc() {
		return xymc;
	}
	public void setXymc(String xymc) {
		this.xymc = xymc;
	}
	
	public String getQsxb() {
		return qsxb;
	}
	public void setQsxb(String qsxb) {
		this.qsxb = qsxb;
	}
	public String getDl() {
		return dl;
	}
	public void setDl(String dl) {
		this.dl = dl;
	}
	public String getRzrs() {
		return rzrs;
	}
	public void setRzrs(String rzrs) {
		this.rzrs = rzrs;
	}
	public String getSfhhqs() {
		return sfhhqs;
	}
	public void setSfhhqs(String sfhhqs) {
		this.sfhhqs = sfhhqs;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public SearchModel getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public String getFpzt() {
		return fpzt;
	}
	public void setFpzt(String fpzt) {
		this.fpzt = fpzt;
	}
	public String getSsyxdm() {
		return ssyxdm;
	}
	public void setSsyxdm(String ssyxdm) {
		this.ssyxdm = ssyxdm;
	}
	public String getSsyxmc() {
		return ssyxmc;
	}
	public void setSsyxmc(String ssyxmc) {
		this.ssyxmc = ssyxmc;
	}
	public String getCzsj() {
		return czsj;
	}
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}
	public String getFpczr() {
		return fpczr;
	}
	public void setFpczr(String fpczr) {
		this.fpczr = fpczr;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	/**
	 * @return the thxy
	 */
	public String getThxy() {
		return thxy;
	}
	/**
	 * @param thxyҪ���õ� thxy
	 */
	public void setThxy(String thxy) {
		this.thxy = thxy;
	}
	/**
	 * @return the thr
	 */
	public String getThr() {
		return thr;
	}
	/**
	 * @param thrҪ���õ� thr
	 */
	public void setThr(String thr) {
		this.thr = thr;
	}
	
	
	
	
	

}
